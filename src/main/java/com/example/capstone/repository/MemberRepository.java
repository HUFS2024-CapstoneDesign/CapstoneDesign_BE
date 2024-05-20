package com.example.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}
