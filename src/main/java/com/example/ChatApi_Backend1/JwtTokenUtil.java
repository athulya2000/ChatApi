package com.example.ChatApi_Backend1;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final String JWT_SECRET = generateSecretKey();
    private static final long JWT_EXPIRATION_MS = 3600000; // 1 hour

    private static String generateSecretKey() {
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            byte[] keyBytes = new byte[64];
            random.nextBytes(keyBytes);
            return Base64.getEncoder().encodeToString(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("iat", now);
        claims.put("exp", expiryDate);

        SecretKey secretKey = new SecretKeySpec(JWT_SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
