package com.example.capstone.Service;

import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;

public interface MemberCommandService {
  Member signUpMember(SignUpMemberRequest request);

  TokenResponse login(LoginMemberRequest request);
}
