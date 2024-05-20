package com.example.capstone.Service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.capstone.Converter.MemberConverter;
import com.example.capstone.Service.MemberCommandService;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.repository.MemberRepository;
import com.example.capstone.security.provider.JwtAuthProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandCommandServiceImpl implements MemberCommandService {

  private final MemberRepository memberRepository;
  private final JwtAuthProvider jwtAuthProvider;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Member signUpMember(SignUpMemberRequest request) {
    return memberRepository.save(MemberConverter.toMember(request));
  }

  @Override
  public TokenResponse login(LoginMemberRequest request) {
    Member member =
        memberRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

    if (!(member.getPassword().isSamePassword(request.getPassword(), bCryptPasswordEncoder))) {
      throw new MemberException(GlobalErrorCode.PASSWORD_MISMATCH);
    }

    String accessToken = jwtAuthProvider.generateAccessToken(member.getId());
    String refreshToken = jwtAuthProvider.generateRefreshToken(member.getId());

    return MemberConverter.toLoginMemberResponse(member.getId(), accessToken, refreshToken);
  }
}
