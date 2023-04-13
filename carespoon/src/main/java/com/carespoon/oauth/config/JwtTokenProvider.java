package com.carespoon.oauth.config;

import com.carespoon.user.domain.User;
import com.carespoon.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final UserRepository userRepository;

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final long ACCESS_TOKEN_VALID_TIME = 3 * 60 * 60 * 1000L; //3시간

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createJwtAccessToken(String userPk, String name){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("name", name);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    // token 가공해서 정보 추출
    public Authentication getAuthentication(String token) {
        User user = userRepository.findByEmail(Long.parseLong(getUserPk(token)));
        UserDetails sessionUserDTO = SessionUserDTO.builder().user(user).build();
        return new UsernamePasswordAuthenticationToken(sessionUserDTO, "", sessionUserDTO.getAuthorities());
    }

}
