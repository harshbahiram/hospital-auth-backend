package com.harsh.hospital_auth_backend.security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey123456".getBytes());

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }

}
