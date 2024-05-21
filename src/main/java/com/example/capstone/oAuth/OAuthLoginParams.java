package com.example.capstone.oAuth;

import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
  OAuthProvider oAuthProvider();

  MultiValueMap<String, String> makeBody();
}
