package com.StudentManagmentSystem.SMS.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secretkey}")
    private String secretKey;

    private SecretKey getSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        return Jwts.builder()
        .claim("role", user.getRole().name())
        .subject(user.getId().toString())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000*60*15))
        .signWith(getSecretKey())
        .compact();
    }    


    public Integer extractUserId(String token){
        return Integer.parseInt(extractClaims(token, Claims::getSubject));
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw e; 
        }
    }
    

    public boolean validateToken(String token, UserPrincipal user){
        final Integer userId = extractUserId(token);
        return (userId.equals(user.getId()) && ! isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return extractExpireDate(token).before(new Date());
    }
    private Date extractExpireDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }

}