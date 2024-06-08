package com.example.capstone.dto.response;

import java.util.List;

import com.example.capstone.domain.Enums.Gender;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;

import lombok.*;

public class PetResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class CreatePetResponse {
    Long petId;
    String name;
    Gender gender;
    Integer age;
    String species;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class PetResponse {
    String name;
    Gender gender;
    Integer age;
    String species;
    List<DiagnosisResponse> diagnosisList;
  }
}
