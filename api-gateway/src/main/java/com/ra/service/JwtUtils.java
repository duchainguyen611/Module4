package com.ra.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUtils {
    private static final String SECRET_KEY="NguyenDucHaiProVIP61102";
    public void validateToken(String token){
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }
    public String getRoleNameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
//    public List<String> getTienbody(String token){
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        return (List<String>) claims.get("testList");
//    }

}
