package com.example.capstone.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;
import com.example.capstone.repository.PetRepository;
import com.example.capstone.service.PetQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class petQueryServiceImpl implements PetQueryService {

  private final PetRepository petRepository;

  @Override
  public List<Pet> findPetByMember(Member member) {
    return petRepository.findAllByMemberId(member.getId());
  }
}
