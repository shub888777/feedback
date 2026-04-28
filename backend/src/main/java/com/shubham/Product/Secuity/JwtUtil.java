package com.shubham.Product.Secuity;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class JwtUtil {

    private final String JWT_TOKEN = "bXlzdXBlcnNlY3JldGtleW15c3VwZXJzZWNyZXRrZXk=";
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claim =new HashMap<>();
        claim.put("roles",userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claim)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+6000000))
                .signWith(getKey())
                .compact();
    }
    public SecretKey getKey(){
        byte [] KeyByte= Decoders.BASE64.decode(JWT_TOKEN);
        return Keys.hmacShaKeyFor(KeyByte);
    }
    private Claims getallClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public boolean isTokenVaLid(String token,UserDetails userDetails){
        System.out.println("Username from token: " + extractUsername(token));
        System.out.println("Username from DB: " + userDetails.getUsername());
        System.out.println("Expired: " + isTokenExpired(token));
        return extractUsername(token).equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
        return getallClaims(token)
                .getExpiration()
                .before(new Date());
    }
    public  String extractUsername(String token){

        return getallClaims(token)
                .getSubject();
    }
    public List<String> extractRoles(String token){
        return getallClaims(token).get("roles",List.class);
    }
}
