package com.example.capstone.domain;

import jakarta.persistence.*;

import com.example.capstone.domain.common.BaseEntity;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Diagnosis extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer medicalExpense;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pet_id")
  private Pet pet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "disease_id")
  private Disease disease;
}
