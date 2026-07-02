package com.example.cajero.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "clave-super-secreta-para-firmar-jwt-cajero-2026";
    private static final long EXPIRATION_TIME = 86400000;

    private JwtUtil() {
    }

    public static String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String obtenerUsername(String token) {
        return obtenerClaims(token).getSubject();
    }

    public static boolean validarToken(String token) {
        try {
            obtenerClaims(token);
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    private static Claims obtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }
}
