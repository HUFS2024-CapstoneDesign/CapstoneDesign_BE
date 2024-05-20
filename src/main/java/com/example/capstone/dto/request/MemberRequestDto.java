package com.example.capstone.dto.request;

import lombok.Getter;

public class MemberRequestDto {

  @Getter
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
}
