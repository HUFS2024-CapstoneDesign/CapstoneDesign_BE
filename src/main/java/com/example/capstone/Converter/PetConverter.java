package com.example.capstone.Converter;

import java.util.List;

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

  public static PetResponse toPetResponse(Pet pet) {
    return PetResponse.builder()
        .name(pet.getName())
        .gender(pet.getGender())
        .age(pet.getAge())
        .species(pet.getSpecies())
        .diagnosisList(DiagnosisConverter.toDiagnosisResponseList(pet.getDiagnosisList()))
        .build();
  }

  public static List<PetResponse> toPetResponseList(List<Pet> petList) {
    return petList.stream().map(pet -> PetConverter.toPetResponse(pet)).toList();
  }
}
