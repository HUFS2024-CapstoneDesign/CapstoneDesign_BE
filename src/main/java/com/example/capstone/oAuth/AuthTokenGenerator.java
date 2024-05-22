package com.example.capstone.oAuth;

import org.springframework.stereotype.Component;

import com.example.capstone.security.provider.JwtAuthProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokenGenerator {

  private static final String BEARER_TYPE = "Bearer";

  private final JwtAuthProvider jwtAuthProvider;

  public AuthToken generate(Long memberId) {

    String accessToken = jwtAuthProvider.generateAccessToken(memberId);
    String refreshToken = jwtAuthProvider.generateRefreshToken(memberId);

    return AuthToken.of(accessToken, refreshToken);
  }
}
