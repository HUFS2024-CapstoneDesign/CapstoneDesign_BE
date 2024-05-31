package com.example.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {}
