package com.example.capstone.dto.request;

import lombok.Getter;

public class PetRequestDto {

  @Getter
  public static class CreatePetRequest {
    String name;
    String gender;
    Integer age;
    String species;
  }
}
