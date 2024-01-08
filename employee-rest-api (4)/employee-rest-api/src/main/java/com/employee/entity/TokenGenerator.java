package com.employee.entity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class TokenGenerator {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String l, long expirationTimeMillis) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeMillis);

        return Jwts.builder()
                .setSubject(l)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }
}