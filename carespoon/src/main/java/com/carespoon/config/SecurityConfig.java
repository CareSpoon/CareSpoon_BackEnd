package com.carespoon.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthTokenProvider authTokenProvider;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/v/api-docs","/configuration/**","/swagger*/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() // preflight 대응
                .antMatchers("/auth/**").permitAll() // /auth/**에 대한 접근을 인증 절차 없이 허용(로그인 관련 url)
                // 특정 권한을 가진 사용자만 접근을 허용해야 할 경우, 하기 항목을 통해 가능
                //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated() // 위에서 따로 지정한 접근허용 리소스 설정 후 그 외 나머지 리소스들은 무조건 인증을 완료해야 접근 가능
                .and()
                .headers() // 아래에 X-Frame-Option 헤더 설정을 위해 headers() 작성
                .frameOptions().sameOrigin() // 동일 도메인에서는 iframe 접근 가능하도록 X-Frame-Options을 smaeOrigin()으로 설정
                .and()
                .cors()
                .and()
                .csrf().disable()
                // 예외 처리를 하고 싶다면 아래와 같이 작성 가능합니다.
                //.exceptionHandling() // 예외 처리 지정
                //.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                //.accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // 커스텀 필터 등록하며, 기존에 지정된 필터에 앞서 실행

    }
}
