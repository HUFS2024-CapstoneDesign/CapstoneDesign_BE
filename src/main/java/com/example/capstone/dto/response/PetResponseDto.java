package com.example.capstone.dto.response;

import com.example.capstone.domain.Enums.Gender;

import lombok.*;

public class PetResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class CreatePetResponse {
    String name;
    Gender gender;
    Integer age;
    String species;
  }
}
