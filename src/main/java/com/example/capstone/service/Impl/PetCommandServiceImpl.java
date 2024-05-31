package com.example.capstone.service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.capstone.Converter.PetConverter;
import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.PetRequestDto.*;
import com.example.capstone.repository.PetRepository;
import com.example.capstone.service.PetCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PetCommandServiceImpl implements PetCommandService {

  private final PetRepository petRepository;

  @Override
  public Pet createPet(Member member, CreatePetRequest request) {
    return petRepository.save(PetConverter.toPet(member, request));
  }
}
