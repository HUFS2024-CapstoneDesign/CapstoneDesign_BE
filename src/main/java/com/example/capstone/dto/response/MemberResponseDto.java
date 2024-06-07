package com.example.capstone.dto.response;

import lombok.*;

public class MemberResponseDto {

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SignUpMemberResponse {
    Long memberId;
    String nickName;
    String email;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class TokenResponse {
    Long memberId;
    String accessToken;
    String refreshToken;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FindEmailByNickNameResponse {
    String email;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class ChangePasswordResponse {
    Long memberId;
    String email;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SetAddressResponse {
    Long memberId;
    String address;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SetNickNameResponse {
    Long memberId;
    String nickName;
  }

  @Getter
  @Builder
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class GetMemberResponse {
    Long memberId;
    String email;
    String nickName;
    String address;
    String profileImage;
  }
}
