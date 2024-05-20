package com.example.capstone.Service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.capstone.Converter.MemberConverter;
import com.example.capstone.Service.MemberCommandService;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.SignUpMemberRequest;
import com.example.capstone.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandCommandServiceImpl implements MemberCommandService {

  private final MemberRepository memberRepository;

  @Override
  public Member signUpMember(SignUpMemberRequest request) {
    return memberRepository.save(MemberConverter.toMember(request));
  }
}
