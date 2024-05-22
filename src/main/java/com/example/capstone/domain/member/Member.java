package com.example.capstone.domain.member;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.example.capstone.domain.Enums.SocialType;
import com.example.capstone.domain.Pet;

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

  @Embedded private Password password;

  private String nickName;

  @Builder.Default private SocialType socialType = SocialType.COMMON;

  private String address;

  private String profileImage;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Pet> petList = new ArrayList<>();

  public void setSocialType(SocialType socialType) {
    this.socialType = socialType;
  }
}
