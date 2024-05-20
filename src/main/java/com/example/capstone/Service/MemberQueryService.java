package com.example.capstone.Service;

import com.example.capstone.domain.member.Member;

public interface MemberQueryService {

  Member findMemberById(Long memberId);
}
