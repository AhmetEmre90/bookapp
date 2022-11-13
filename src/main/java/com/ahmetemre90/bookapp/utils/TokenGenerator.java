package com.ahmetemre90.bookapp.utils;

import com.ahmetemre90.bookapp.exception.GenericException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TokenGenerator {

    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRATION_TIME_IN_MINUTE}")
    private long EXPIRATION_TIME_IN_MINUTE;

    public String generateToken(Authentication authentication) {

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRATION_TIME_IN_MINUTE * 60 * 1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyJWT(String token) {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY.getBytes())).acceptExpiresAt(20).build();

        try {
            return verifier.verify(token);
        } catch (Exception ex) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorMessage(ex.getMessage())
                    .build();
        }
    }
}
