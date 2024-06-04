package com.example.capstone.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.capstone.domain.member.Member;
import com.example.capstone.domain.member.QMember;
import com.example.capstone.repository.MemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

  private final JPAQueryFactory query;

  @Override
  public Optional<Member> findByEmail(String email) {
    return Optional.ofNullable(
        query.selectFrom(QMember.member).where(QMember.member.email.eq(email)).fetchOne());
  }
}
