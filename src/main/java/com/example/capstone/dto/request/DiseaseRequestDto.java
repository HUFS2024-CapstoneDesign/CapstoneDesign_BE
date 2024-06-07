package com.example.capstone.dto.request;

import lombok.Getter;

public class DiseaseRequestDto {

  @Getter
  public static class CreateDiseaseRequest {
    String code;
    String name;
    String explanation;
    String symptom1;
    String symptom2;
    String symptom3;
    String causes;
  }
}
