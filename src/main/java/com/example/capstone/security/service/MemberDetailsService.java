package com.example.capstone.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.capstone.domain.member.Member;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.repository.MemberRepository;
import com.example.capstone.security.domain.MemberDetails;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    Member member =
        memberRepository
            .findById(Long.parseLong(memberId))
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

    return new MemberDetails(member);
  }
}
