package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateAccessToken(UserEntity userEntity) {
        return Jwts.builder()
                .subject(String.valueOf(userEntity.getId()))
                .claim("userEmail", userEntity.getEmail())
                .claim("roles", userEntity.getRoles())
                .claim("tokenType", "ACCESS")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *10)) // 10 min
                .signWith(getSecretKey())
                .compact();
    }


    public String generateRefreshToken(UserEntity userEntity) {
        return Jwts.builder()
                .subject(String.valueOf(userEntity.getId()))
                .claim("tokenType", "REFRESH")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6)) // 6 months
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromJwtToken(String jwtToken) {

        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }


    public String getTokenType(String jwtToken) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return claims.get("tokenType", String.class);

    }
}
