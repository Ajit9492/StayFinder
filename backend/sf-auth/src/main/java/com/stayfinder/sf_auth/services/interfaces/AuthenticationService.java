package com.stayfinder.sf_auth.services.interfaces;

import com.stayfinder.sf_auth.dto.AuthenticationRequest;
import com.stayfinder.sf_auth.dto.AuthenticationResponse;
import com.stayfinder.sf_auth.dto.RegisterUserRequestDTO;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterUserRequestDTO request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
