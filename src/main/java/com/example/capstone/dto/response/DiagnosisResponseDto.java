package com.example.capstone.dto.response;

import java.time.LocalDateTime;

import lombok.*;

public class DiagnosisResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class DiagnosisResponse {
    String diseaseName;
    LocalDateTime createdAt;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class CreateDiagnosisResponse {
    Long id;
    Integer medicalExpense;
    String petName;
    String diseaseName;
    LocalDateTime createdAt;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class GetDiagnosisResponse {
    String diseaseName;
    String explanation;
    String symptom1;
    String symptom2;
    String symptom3;
    String cause;
    Integer medicalExpense;
  }
}
