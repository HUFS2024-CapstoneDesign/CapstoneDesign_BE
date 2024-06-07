package com.example.capstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone.domain.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
  Optional<Disease> findByCode(String code);
}
