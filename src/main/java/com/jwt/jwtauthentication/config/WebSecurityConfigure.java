package com.jwt.jwtauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jwt.jwtauthentication.exceptions.JwtAccessDeniedHandler;
import com.jwt.jwtauthentication.exceptions.JwtAuthenticationEntryPoint;
import com.jwt.jwtauthentication.infrastructure.TokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfigure {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // ignore h2 database API
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF config Disable
    http.csrf().disable()

        // add jwtAuthenticationEntryPoint and jwtAccessDeniedHandler when exception handling is called
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .accessDeniedHandler(jwtAccessDeniedHandler)

        // config for h2-console
        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        // session off as STATELESS
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // requests handling
        .and()
        .authorizeRequests()
        .antMatchers("/", "/home").authenticated()
        .anyRequest().permitAll()

        // JwtSecurityConfig as JwtFilter
        .and()
        .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
