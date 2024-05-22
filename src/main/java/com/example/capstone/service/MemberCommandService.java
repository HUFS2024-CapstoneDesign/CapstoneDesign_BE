package com.example.capstone.service;

import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;
import com.example.capstone.oAuth.AuthToken;
import com.example.capstone.oAuth.OAuthInfoResponse;
import com.example.capstone.oAuth.OAuthLoginParams;

public interface MemberCommandService {
  Member signUpMember(SignUpMemberRequest request);

  TokenResponse login(LoginMemberRequest request);

  Member createKakaoMember(OAuthInfoResponse response);

  AuthToken loginKakao(OAuthLoginParams params);

  TokenResponse reissue(ReissueRequest request);
}
