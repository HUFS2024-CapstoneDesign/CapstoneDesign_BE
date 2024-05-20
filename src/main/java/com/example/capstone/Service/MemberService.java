package com.example.capstone.Service;

import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.SignUpMemberRequest;

public interface MemberService {
  Member signUpMember(SignUpMemberRequest request);
}
