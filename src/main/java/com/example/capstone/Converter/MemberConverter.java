package com.example.capstone.Converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.capstone.domain.member.Member;
import com.example.capstone.domain.member.Password;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;

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
}
