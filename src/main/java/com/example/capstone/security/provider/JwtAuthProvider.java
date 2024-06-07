package com.example.capstone.security.provider;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.capstone.domain.member.Member;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.TokenException;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthProvider {

  private final SecretKey secretKey;
  private final long accessTokenValidityMilliseconds;
  private final long refreshTokenValidityMilliseconds;
  private final RedisTemplate<String, String> redisTemplate;

  public JwtAuthProvider(
      @Value("${jwt.secret}") final String secretKey,
      @Value("${jwt.access-token-validity}") final long accessTokenValidityMilliseconds,
      @Value("${jwt.refresh-token-validity}") final long refreshTokenValidityMilliseconds,
      RedisTemplate<String, String> redisTemplate) {
    this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
    this.refreshTokenValidityMilliseconds = refreshTokenValidityMilliseconds;
    this.redisTemplate = redisTemplate;
  }

  public String generateAccessToken(Long userId) {
    return generateToken(userId, accessTokenValidityMilliseconds);
  }

  public String generateRefreshToken(Long userId) {
    String refreshToken = generateToken(userId, refreshTokenValidityMilliseconds);
    redisTemplate
        .opsForValue()
        .set(
            userId.toString(),
            refreshToken,
            refreshTokenValidityMilliseconds,
            TimeUnit.MILLISECONDS);
    return refreshToken;
  }

  private String generateToken(Long userId, long validityMilliseconds) {
    Claims claims = Jwts.claims();

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userId.toString())
        .setIssuedAt(Date.from(now.toInstant()))
        .setExpiration(Date.from(tokenValidity.toInstant()))
        .setIssuer("CapStone")
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public Long getSubject(String token) {
    return Long.valueOf(getClaims(token).getBody().getSubject());
  }

  private Jws<Claims> getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
  }

  public boolean isTokenValid(String token) {
    try {
      Jws<Claims> claims = getClaims(token);
      Date expiredDate = claims.getBody().getExpiration();
      return expiredDate.after(new Date()) && !isTokenBlacklisted(token);
    } catch (ExpiredJwtException e) {
      throw new RuntimeException();
    } catch (SecurityException
        | MalformedJwtException
        | UnsupportedJwtException
        | IllegalArgumentException e) {
      throw new RuntimeException();
    }
  }

  public Long parseRefreshToken(String token) {
    if (isTokenValid(token)) {
      Claims claims = getClaims(token).getBody();
      return Long.parseLong(claims.getSubject());
    }
    throw new TokenException(GlobalErrorCode.INVALID_TOKEN);
  }

  public Long getExpiration(String accessToken) {
    // accessToken 남은 유효시간
    Date expiration =
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(accessToken)
            .getBody()
            .getExpiration();
    // 현재 시간
    Long now = new Date().getTime();
    return (expiration.getTime() - now);
  }

  public void logoutUser(Member member, String token) {
    System.out.println(token);

    String atk = token.substring(7);

    System.out.println(atk);

    Long expiration = getExpiration(atk);

    String redisKey = member.getId().toString();

    // 1. Redis에서 사용자의 refreshToken 삭제
    redisTemplate.delete(redisKey);

    // 2. 블랙리스트에 JWT 토큰 추가
    redisTemplate.opsForValue().set(atk, "logout", Duration.ofMillis(expiration));
  }

  private boolean isTokenBlacklisted(String token) {
    String value = redisTemplate.opsForValue().get(token);
    return "logout".equals(value);
  }
}
