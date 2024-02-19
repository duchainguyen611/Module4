package com.ra.security.jwt;

import com.ra.security.user_principal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${expired}")
    private Long EXPIRED;
    @Value("${secret_key}")
    private String SECRET_KEY;
    private final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    public String generateToken(UserPrinciple userPrinciple){
        String roleName=userPrinciple.getAuthorities().stream().toList().get(0).toString();
        System.out.println(roleName);
//        List<String> testList=new ArrayList<>();
//        testList.add("abc");
//        testList.add("def");
        return Jwts.builder().setSubject(roleName).
                claim("userId",userPrinciple.getUser().getId())
//                claim("userName",userPrinciple.getUser().getFullName())
//                .claim("testList",testList).
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+EXPIRED)).
                signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException expiredJwtException){
            logger.error("Expried Token {}",expiredJwtException.getMessage());
        }catch (SignatureException signatureException){
            logger.error("Invalid Signature Token {}",signatureException.getMessage());
        }catch (MalformedJwtException malformedJwtException) {
            logger.error("Invalid format {}", malformedJwtException.getMessage());
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

}
