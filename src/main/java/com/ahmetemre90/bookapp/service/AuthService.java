package com.ahmetemre90.bookapp.service;

import com.ahmetemre90.bookapp.dto.ErrorCode;
import com.ahmetemre90.bookapp.dto.LoginRequest;
import com.ahmetemre90.bookapp.dto.TokenResponse;
import com.ahmetemre90.bookapp.exception.GenericException;
import com.ahmetemre90.bookapp.utils.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public String getLoggedInUsername() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public TokenResponse login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            TokenResponse tokenResponse = TokenResponse.builder()
                    .accessToken(tokenGenerator.generateToken(auth))
                    .userResponse(userService.getUser(loginRequest.getUsername()))
                    .build();
            return tokenResponse;
        } catch (Exception ex) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(ErrorCode.USER_NOT_FOUND)
                    .errorMessage("user not found!")
                    .build();
        }
    }
}
