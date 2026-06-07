package com.microservice.ecart.orderservice.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class OrderJwtUtil {

    // ⚠️ MUST match the exact secret key used in your Gateway's JwtUtil
    private final String SECRET_KEY = "your32characterlongsecretkeyhere12345";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Validates the token and extracts the user email payload
    public String validateAndExtractEmail(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Returns the email string embedded inside the token
    }
}