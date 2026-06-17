package com.usuario.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generarToken(String email) {
        SecretKey key = new SecretKeySpec(
            secret.getBytes(StandardCharsets.UTF_8),
            "HmacSHA256"
        );
        Instant ahora = Instant.now();
        return Jwts.builder()
            .subject(email)
            .issuedAt(Date.from(ahora))
            .expiration(Date.from(ahora.plusSeconds(3600)))
            .signWith(key)
            .compact();
    }
}
