package com.example.capstone.Converter;

import org.springframework.stereotype.Component;

import com.example.capstone.domain.Enums.Gender;
import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.PetRequestDto.*;
import com.example.capstone.dto.response.PetResponseDto.*;

@Component
public class PetConverter {

  public static Pet toPet(Member member, CreatePetRequest request) {
    return Pet.builder()
        .name(request.getName())
        .gender(Gender.valueOf(request.getGender()))
        .age(request.getAge())
        .species(request.getSpecies())
        .member(member)
        .build();
  }

  public static CreatePetResponse toCreatePetResponse(Pet pet) {
    return CreatePetResponse.builder()
        .name(pet.getName())
        .gender(pet.getGender())
        .age(pet.getAge())
        .species(pet.getSpecies())
        .build();
  }
}
