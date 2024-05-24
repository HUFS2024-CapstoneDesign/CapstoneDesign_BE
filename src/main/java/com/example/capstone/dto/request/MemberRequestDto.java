package com.example.capstone.dto.request;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDto {

  @Getter
  @Builder
  public static class SignUpMemberRequest {
    String nickName;
    String email;
    String password;
    String address;
  }

  @Getter
  public static class LoginMemberRequest {
    String email;
    String password;
  }

  @Getter
  public static class ReissueRequest {
    String refreshToken;
  }

  @Getter
  public static class FindPasswordByEmailRequest {
    String email;
  }

  @Getter
  public static class VerifyCodeRequest {
    String email;
    String code;
  }

  @Getter
  public static class ChangePasswordRequest {
    String password;
  }

  @Getter
  public static class IsDuplicatedNickNameRequest {
    String nickName;
  }

  @Getter
  public static class IsDuplicatedEmailRequest {
    String email;
  }
}
