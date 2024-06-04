package com.example.capstone.repository;

import java.util.Optional;

import com.example.capstone.domain.member.Member;

public interface MemberCustomRepository {

  Optional<Member> findByEmail(String email);
}
