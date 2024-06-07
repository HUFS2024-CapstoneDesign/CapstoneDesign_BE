package com.example.capstone.service;

import java.util.List;

import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;

public interface PetQueryService {

  List<Pet> findPetByMember(Member member);
}
