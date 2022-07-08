package com.soulcode.Servicos.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class JWTUtils {
    @Value("${jwt.expiration}")
    public Long expiration;

    @Value("${jwt.secret}")
    public String secret;

    public String generateToken(String login) {
        return JWT.create()
                .withSubject(login)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret));
    }

    public String getLogin(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getSubject();
    }
}
