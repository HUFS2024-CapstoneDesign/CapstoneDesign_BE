package com.example.capstone.service;

import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.PetRequestDto.*;

public interface PetCommandService {

  Pet createPet(Member member, CreatePetRequest request);
}
