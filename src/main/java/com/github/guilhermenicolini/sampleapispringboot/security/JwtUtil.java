package com.github.guilhermenicolini.sampleapispringboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    public String generateToken(String id, String email, String name) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setAudience(audience)
                .setSubject(email)
                .setId(id)
                .claim("name", name)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean validToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            return userName != null && expirationDate != null;
        }
        return false;
    }

    public String getClaim(String key, String token) {
        Claims claims = getClaims(token);
        return claims != null && claims.get(key) != null ? claims.get(key).toString() : null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}