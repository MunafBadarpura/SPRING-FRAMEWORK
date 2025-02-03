package com.munaf.A13_SPRING_SECURITY_1.services;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8)); // this is used to create SecretKey
    }


    // TAKES USER ENTITY AND GENERATE JWT ACCESS TOKEN
    public String generateAccessToken(User user) {
        return Jwts.builder() // builder is used to create a token
                .subject(user.getId().toString()) // subject is something to identify user
                .claim("email", user.getEmail()) // claims are key-value pairs for payloads
                .claim("roles", user.getRoles().toString())
                .issuedAt(new Date()) // specifies the timestamp when the token was issued
                .expiration(new Date(System.currentTimeMillis() + 1000*60)) // specifies the expiration timestamp of the token.
                .signWith(getSecretKey()) // Specifies the signing key and the algorithm
                .compact(); // returns URL-safe string.
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();
    }


    // TAKES JWT TOKEN AND RETURN USER ID
    public Long getUserIdFromToken(String token) {
        // Claims = Represents the payload of the JWT.
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey()) // verify the SecretKey.
                .build() // Parses the provided JWT token and verifies its signature using the configured SecretKey, Returns Jwt<Claims>
                .parseSignedClaims(token) //
                .getPayload(); // Extracts the payload (claims) from the parsed token.
        return Long.valueOf(claims.getSubject());
    }

    // COMPLETE WORKFLOW
    // 1. A JWT token is passed as input to the method.
    // 2. The method configures a JWT parser with the secret key to verify the token's signature.
    // 3. The token is parsed, and its signature is validated.
    // 4. The payload (claims) is extracted from the token.
    // 5. The subject claim, which represents the user ID, is retrieved.
    // 6. The subject is converted to a Long and returned.


}
