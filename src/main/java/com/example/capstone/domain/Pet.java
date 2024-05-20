package com.example.capstone.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.example.capstone.domain.Enums.Gender;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Gender gender;

  private Integer age;

  private String species;

  private String petImage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
  private List<Diagnosis> diagnosisList = new ArrayList<>();
}
