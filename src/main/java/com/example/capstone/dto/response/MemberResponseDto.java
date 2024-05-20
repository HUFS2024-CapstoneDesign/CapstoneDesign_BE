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
}
