//package com.adrianlapos.teams.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenUtil {
//
//    // Secret key to sign the JWT token (you should store this securely)
//    private static final String SECRET_KEY = "your_secret_key_here"; // Replace with a strong secret key
//
//    private static final long JWT_EXPIRATION = 60 * 60 * 1000; // Token validity (1 hour)
//
//    // Generate a JWT token for a specific username
//    public String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }
//
//    // Extract username from the JWT token
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    // Extract claims (payload) from the JWT token
//    public Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // Extract specific claim from the JWT token
//    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimsResolver.resolve(claims);
//    }
//
//    // Validate the JWT token
//    public boolean validateToken(String token, String username) {
//        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
//    }
//
//    // Check if the token is expired
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // Extract expiration date from the JWT token
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    // Functional interface to extract specific claims
//    @FunctionalInterface
//    public interface ClaimsResolver<T> {
//        T resolve(Claims claims);
//    }
//}
