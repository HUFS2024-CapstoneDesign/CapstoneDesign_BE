package com.example.capstone.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.example.capstone.domain.common.BaseEntity;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Disease extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;

  private String name;

  private String explanation;

  private String symptom1;

  private String symptom2;

  private String symptom3;

  private String causes;

  @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL)
  private List<Diagnosis> diagnosisList = new ArrayList<>();
}
