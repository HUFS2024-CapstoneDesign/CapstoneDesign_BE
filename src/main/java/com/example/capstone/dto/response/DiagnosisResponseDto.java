package com.example.capstone.dto.response;

import java.time.LocalDateTime;

import lombok.*;

public class DiagnosisResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class DiagnosisResponse {
    String disease;
    LocalDateTime createAt;
  }
}
