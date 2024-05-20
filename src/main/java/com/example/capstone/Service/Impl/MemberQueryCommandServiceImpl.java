package com.example.capstone.Service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.capstone.Service.MemberQueryService;
import com.example.capstone.domain.member.Member;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryCommandServiceImpl implements MemberQueryService {

  private final MemberRepository memberRepository;

  @Override
  public Member findMemberById(Long memberId) {
    return memberRepository
        .findById(memberId)
        .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));
  }
}
