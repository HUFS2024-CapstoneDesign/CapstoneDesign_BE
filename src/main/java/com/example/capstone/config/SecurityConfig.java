package com.example.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.capstone.security.filter.JwtAuthExceptionHandlingFilter;
import com.example.capstone.security.filter.JwtRequestFilter;
import com.example.capstone.security.handler.JwtAccessDeniedHandler;
import com.example.capstone.security.handler.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtRequestFilter jwtRequestFilter;
  private final JwtAuthExceptionHandlingFilter jwtAuthExceptionHandlingFilter;

  private final String[] allowedUrls = {
    "/h2-console/**",
    "api/v1/members/signup",
    "api/v1/members/login",
    "api/v1/members/kakao",
    "api/v1/members/reissue",
    "api/v1/members/find-id",
    "api/v1/members/find-password",
    "api/v1/members/check-code",
    "api/v1/members/change-password",
    "api/v1/members/check-nickname",
    "api/v1/members/check-email"
  };

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web ->
        web.ignoring()
            .requestMatchers(
                "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/health");
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(AbstractHttpConfigurer::disable);
    http.httpBasic(AbstractHttpConfigurer::disable);
    http.csrf(AbstractHttpConfigurer::disable);

    http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

    http.sessionManagement(
        sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.exceptionHandling(
        (configurer ->
            configurer
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)));

    http.authorizeHttpRequests(
        (authorize) ->
            authorize.requestMatchers(allowedUrls).permitAll().anyRequest().authenticated());

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtAuthExceptionHandlingFilter, JwtRequestFilter.class);

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
