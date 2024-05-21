package com.example.capstone.service.Impl;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.capstone.Converter.MemberConverter;
import com.example.capstone.domain.Enums.SocialType;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.oAuth.*;
import com.example.capstone.repository.MemberRepository;
import com.example.capstone.security.provider.JwtAuthProvider;
import com.example.capstone.service.MemberCommandService;
import com.example.capstone.service.MemberQueryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandCommandServiceImpl implements MemberCommandService {

  private final MemberRepository memberRepository;
  private final JwtAuthProvider jwtAuthProvider;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final MemberQueryService memberQueryService;
  private final RequestOAuthInfoService requestOAuthInfoService;
  private final AuthTokenGenerator authTokenGenerator;

  @Value("${spring.virtual.password}")
  private String password;

  @Override
  public Member signUpMember(SignUpMemberRequest request) {
    return memberRepository.save(MemberConverter.toMember(request));
  }

  @Override
  public TokenResponse login(LoginMemberRequest request) {
    Member member = memberQueryService.findMemberByEmail(request.getEmail());

    if (!(member.getPassword().isSamePassword(request.getPassword(), bCryptPasswordEncoder))) {
      throw new MemberException(GlobalErrorCode.PASSWORD_MISMATCH);
    }

    String accessToken = jwtAuthProvider.generateAccessToken(member.getId());
    String refreshToken = jwtAuthProvider.generateRefreshToken(member.getId());

    return MemberConverter.toLoginMemberResponse(member.getId(), accessToken, refreshToken);
  }

  @Override
  public Member createKakaoMember(OAuthInfoResponse response) {

    Optional<Member> findMember = memberQueryService.findMemberByNickName(response.getNickname());

    if (findMember.isPresent()) {
      return findMember.get();
    } else {
      Member createMember = signUpMember(MemberConverter.toMemberKakaoRequest(response, password));
      createMember.setSocialType(SocialType.KAKAO);
      return createMember;
    }
  }

  @Override
  public AuthToken loginKakao(OAuthLoginParams params) {
    OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
    Long userId = createKakaoMember(oAuthInfoResponse).getId();
    return authTokenGenerator.generate(userId);
  }
}
