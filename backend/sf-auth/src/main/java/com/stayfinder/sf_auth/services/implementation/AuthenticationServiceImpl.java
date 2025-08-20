package com.stayfinder.sf_auth.services.implementation;


import com.stayfinder.sf_auth.dto.AuthenticationRequest;
import com.stayfinder.sf_auth.dto.AuthenticationResponse;
import com.stayfinder.sf_auth.dto.RegisterUserRequestDTO;
import com.stayfinder.sf_auth.dto.UserDTO;

import com.stayfinder.sf_auth.exceptions.UserAlreadyExistsException;
import com.stayfinder.sf_auth.feign.UserClient;
import com.stayfinder.sf_auth.services.interfaces.AuthenticationService;
import feign.FeignException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtServiceImpl;

    private final AuthenticationManager authenticationManager;

    private final UserClient userClient;

    public AuthenticationResponse register(RegisterUserRequestDTO request) {
        try {
            UserDTO userDto = userClient.registerUser(request).getBody();
            assert userDto != null;
            var jwtToken = jwtServiceImpl.generateToken(userDto);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (FeignException.Conflict e) { // only 409 from user service
            throw new UserAlreadyExistsException("User already exists, please login!");
        } catch (FeignException e) {
            throw new RuntimeException("User service unavailable: " + e.getMessage());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDTO userDto = userClient.userLogin(request.getEmail()).getBody();
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        assert userDto != null;
        var jwtToken = jwtServiceImpl.generateToken(userDto);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
