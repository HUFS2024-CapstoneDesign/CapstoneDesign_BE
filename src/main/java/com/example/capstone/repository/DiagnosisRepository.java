package com.example.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone.domain.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {}
