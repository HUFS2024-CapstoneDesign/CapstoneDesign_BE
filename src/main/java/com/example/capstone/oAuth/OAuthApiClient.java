package com.example.capstone.oAuth;

public interface OAuthApiClient {
  OAuthProvider oAuthProvider();

  String requestAccessToken(OAuthLoginParams params);

  OAuthInfoResponse requestOauthInfo(String accessToken);
}
