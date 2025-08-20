package com.stayfinder.sf_auth.services.implementation;


import com.stayfinder.sf_auth.dto.AuthenticationRequest;
import com.stayfinder.sf_auth.dto.AuthenticationResponse;
import com.stayfinder.sf_auth.dto.RegisterUserRequestDTO;
import com.stayfinder.sf_auth.dto.UserDTO;

import com.stayfinder.sf_auth.exceptions.UserAlreadyExistsException;
import com.stayfinder.sf_auth.feign.UserClient;
import com.stayfinder.sf_auth.services.interfaces.AuthenticationService;
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

        UserDTO userDto;
        try {
            userDto = userClient.registerUser(request).getBody();
        }catch(RuntimeException e){
            throw new UserAlreadyExistsException("User Already exist, Please login!! ");
        }
        assert userDto != null;
        var jwtToken = jwtServiceImpl.generateToken(userDto);
        return AuthenticationResponse.builder().token(jwtToken).build();
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
