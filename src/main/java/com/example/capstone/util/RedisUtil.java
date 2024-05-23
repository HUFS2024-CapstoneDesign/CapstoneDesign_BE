package com.example.capstone.util;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisUtil {

  private final StringRedisTemplate stringRedisTemplate;

  private final String PREFIX = "code:";
  private final int LIMIT_TIME = 3 * 60; // 인증 유효시간

  public Boolean hasKey(String email) {
    return stringRedisTemplate.hasKey(PREFIX + email);
  }

  // 이메일과 인증코드 등록
  public void createEmailCertification(String email, String code) {
    stringRedisTemplate.opsForValue().set(PREFIX + email, code, Duration.ofSeconds(LIMIT_TIME));
  }

  // 이메일에 해당하는 인증번호 불러오기
  public String getEmailCertification(String email) {
    return stringRedisTemplate.opsForValue().get(PREFIX + email);
  }

  // Redis의 인증번호를 삭제합니다
  public void deleteEmailCertification(String email) {
    stringRedisTemplate.delete(PREFIX + email);
  }
}
