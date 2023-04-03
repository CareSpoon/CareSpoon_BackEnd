package com.carespoon.config;

import com.google.api.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthTokenProvider {

    @Value("${app.auth.tokenExpiry}")
    private String expiry;

    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

    public AuthTokenProvider(@Value("${app.auth.tokenSecret}") String secretKey){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public AuthToken createToken(String id, String expiry){
        Date expiryDate = getExpiryDate(expiry);

        return new AuthToken(id, expiryDate, key);
    }
    public AuthToken convertAuthToken(String token){
        return new AuthToken(token, key);
    }
    public static Date getExpiryDate(String expiry) {
        return new Date(System.currentTimeMillis() + Long.parseLong(expiry));
    }

    public Authentication getAuthentication(AuthToken authToken){
        if(authToken.validate()){

            Claims claims = authToken.getTokenClaims();

            User principle = new User(claims.getSubject(), "", "");
            return new UsernamePasswordAuthenticationToken(principle, authToken);
        }else{
            throw new TokenValidFailedException();
        }
    }

}