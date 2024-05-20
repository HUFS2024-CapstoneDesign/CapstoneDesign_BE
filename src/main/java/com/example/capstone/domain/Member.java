package com.example.capstone.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.example.capstone.domain.Enums.SocialType;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String nickName;

  private SocialType socialType;

  private String address;

  private String profileImage;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Pet> petList = new ArrayList<>();
}
