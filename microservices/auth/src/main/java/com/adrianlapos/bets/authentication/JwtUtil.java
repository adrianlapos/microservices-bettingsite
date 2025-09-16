package com.adrianlapos.bets.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
@Component
public class JwtUtil {

   private static final String SECRET_KEY = "my_supersecret_256char_long_password";
   private static final int EXPIRATION = 86400000;
   private final Key key;
   public JwtUtil(){
      this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
   }

   public String generateToken(Long id,String userName, String email, double balance, Date loginTime){
      return Jwts.builder().setSubject(String.valueOf(id))
              .setSubject(id.toString())
              .addClaims(Map.of(
                      //"name",userName,
                      "username",userName,"email",email,"balance",balance,"loginTime",loginTime)).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION)) .signWith(key, SignatureAlgorithm.HS256).compact();
   }

   public String extractUserName(String token){
      return extractClaims(token).get("username",String.class); // FIXED: Use getSubject() not custom claim
   }

   public String extractId(String token){
      return extractClaims(token).getSubject();
   }

   public String extractEmail(String token){
      return extractClaims(token).get("email",String.class);
   }

   public double extractBalance(String token){
      return extractClaims(token).get("balance",Double.class);
   }

   public Date extractLoginDate(String token){
      return extractClaims(token).get("loginTime",Date.class);
   }

   private Claims extractClaims(String token) {
      return Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token)
              .getBody();
   }
   public boolean isTokenValid(String jwt, UserDetails userDetails) {

      final String username = extractUserName(jwt);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
   }

   public boolean validateToken(String token) {
      try {
         Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token);
         return true;
      } catch (JwtException | IllegalArgumentException e) {
         return false; // Invalid token
      }
   }

   private boolean isTokenExpired(String token){
      return System.currentTimeMillis() > extractClaims(token).getExpiration().getTime();
   }
}
