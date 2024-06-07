package com.example.capstone.dto.request;

import lombok.Getter;

public class DiagnosisRequestDto {

  @Getter
  public static class CreateDiagnosisRequest {
    Integer medicalExpense;
    String diseaseCode;
  }
}
