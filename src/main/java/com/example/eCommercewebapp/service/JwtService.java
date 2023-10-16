package com.example.eCommercewebapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.eCommercewebapp.model.User;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algorithm.key}")
    private String algorithmkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    private  static final String USERNAME_KEY="USERNAME";

    @PostConstruct
    public void postConstruct(){
        algorithm=Algorithm.HMAC256(algorithmkey);
    }


    public String generateJWT(User user){
        return JWT.create()
                .withClaim(USERNAME_KEY,user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);

    }

        public String getUsername(String token){
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
}
