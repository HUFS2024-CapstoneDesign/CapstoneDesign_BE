package com.example.capstone.service;

import java.util.Optional;

import com.example.capstone.domain.member.Member;

public interface MemberQueryService {

  Member findMemberById(Long memberId);

  Optional<Member> findMemberByNickName(String nickName);

  Optional<Member> findMemberByEmail(String email);
}
