package com.example.back.security;

import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JWTUntil {
    @Value("${jwt.signerKey}")
    String signerKey;
    @Value("${jwt.expiration}")
    int expiration;


    public String GenerateAccessToken(User user){
//        header
//        System.out.println("kieu "+ expiration);
        JWSHeader jwsHeader =new JWSHeader(JWSAlgorithm.HS256);
//      payload
        JWTClaimsSet jwtClaimsSet =new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(expiration, ChronoUnit.MINUTES)))
//                .claim("roles",user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                .claim("scope",buildScope(user))
                .claim("email", user.getEmail())
                .claim("dob", user.getDob() != null ? user.getDob().toString() : null)
                .claim("gender", user.getGender())
                .build();
        SignedJWT  signedJWT=new SignedJWT(jwsHeader,jwtClaimsSet);
        try{
            signedJWT.sign(new MACSigner(signerKey.getBytes()));
            return signedJWT.serialize();
        }
        catch (Exception e){
            System.out.println("Loi khi tao token"+e);
            throw new RuntimeException();
        }

    }
    public JWTClaimsSet extractAllClaimsSet(String token){
        try{
            SignedJWT signedJWT =SignedJWT.parse(token);
            return  signedJWT.getJWTClaimsSet();
        }
        catch (ParseException e){
            System.out.println("Loi "+e);
            throw new RuntimeException();
        }
    }
    public String extractUserName(String token){
        return extractAllClaimsSet(token).getSubject();
    }
    public Date extractDate(String token){
        return extractAllClaimsSet(token).getExpirationTime();
    }
    public boolean checkToken(String token ,String userName){
        return userName.equals(extractUserName(token)) && new Date().before(extractDate(token));
    }
    public List<String> buildScope(User user){
        List<String> roles=null;
        if(!CollectionUtils.isEmpty(user.getRoles())){
            roles= user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        }
        return roles;
    }
}
