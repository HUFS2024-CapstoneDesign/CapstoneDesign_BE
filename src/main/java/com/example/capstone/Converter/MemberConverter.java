package com.example.capstone.Converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.capstone.domain.member.Member;
import com.example.capstone.domain.member.Password;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;
import com.example.capstone.oAuth.AuthToken;
import com.example.capstone.oAuth.OAuthInfoResponse;

@Component
public class MemberConverter {

  public static Member toMember(SignUpMemberRequest request) {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return Member.builder()
        .nickName(request.getNickName())
        .email(request.getEmail())
        .password(Password.encrypt(request.getPassword(), encoder))
        .address(request.getAddress())
        .build();
  }

  public static SignUpMemberResponse toSignUpMemberResponse(Member member) {
    return SignUpMemberResponse.builder()
        .memberId(member.getId())
        .nickName(member.getNickName())
        .email(member.getEmail())
        .build();
  }

  public static TokenResponse toLoginMemberResponse(
      Long memberId, String accessToken, String refreshToken) {
    return TokenResponse.builder()
        .memberId(memberId)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public static TokenResponse toKakaoLogin(AuthToken authToken) {
    return TokenResponse.builder()
        .accessToken(authToken.getAccessToken())
        .refreshToken(authToken.getRefreshToken())
        .build();
  }

  public static SignUpMemberRequest toMemberKakaoRequest(
      OAuthInfoResponse oAuthInfoResponse, String password) {
    return SignUpMemberRequest.builder()
        .nickName(oAuthInfoResponse.getNickname())
        .password(password)
        .build();
  }

  public static TokenResponse toReissueResponse(
      Long memberId, String newAccessToken, String newRefreshToken) {
    return TokenResponse.builder()
        .memberId(memberId)
        .accessToken(newAccessToken)
        .refreshToken(newRefreshToken)
        .build();
  }

  public static FindEmailByNickNameResponse toFindEmailByNickNameResponse(Member member) {
    return FindEmailByNickNameResponse.builder().email(member.getEmail()).build();
  }

  public static ChangePasswordResponse toChangePasswordResponse(Member member) {
    return ChangePasswordResponse.builder()
        .memberId(member.getId())
        .email(member.getEmail())
        .build();
  }

  public static SetAddressResponse toSetAddressResponse(Member member) {
    return SetAddressResponse.builder()
        .memberId(member.getId())
        .address(member.getAddress())
        .build();
  }

  public static SetNickNameResponse toSetNickNameResponse(Member member) {
    return SetNickNameResponse.builder()
        .memberId(member.getId())
        .nickName(member.getNickName())
        .build();
  }

  public static GetMemberResponse toGetMemberResponse(Member member) {
    return GetMemberResponse.builder()
        .memberId(member.getId())
        .email(member.getEmail())
        .nickName(member.getNickName())
        .address(member.getAddress())
        .profileImage(member.getProfileImage())
        .build();
  }
}
