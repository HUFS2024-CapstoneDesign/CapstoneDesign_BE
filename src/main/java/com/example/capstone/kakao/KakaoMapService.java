package com.example.capstone.kakao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.capstone.domain.member.Member;

import lombok.*;

@Service
@RequiredArgsConstructor
public class KakaoMapService {

  @Value("${spring.oauth.kakao.url.map}")
  private String uri;

  @Value("${spring.oauth.kakao.client-id}")
  private String key;

  private final RestTemplate restTemplate;

  private final Integer radius = 5000;
  private final String query = "동물병원";

  @Getter
  @Builder
  public static class KakaoMapResponse {
    String x;
    String y;
    String place_name;
    String place_url;
    String address_name;
    String road_address_name;
    String distance;
    String phone;
  }

  @Getter
  public class Coordinates {
    private Double x;
    private Double y;

    public Coordinates(Double x, Double y) {
      this.x = x;
      this.y = y;
    }
  }

  public Coordinates convertAddressToCoordinates(Member member) {
    String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + member.getAddress();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "KakaoAK " + key);
    headers.set("content-type", "application/json;charset=UTF-8");
    HttpEntity<?> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    System.out.println(response);

    if (response.getStatusCodeValue() == 200) {
      String responseBody = response.getBody();
      JSONObject jsonObject = new JSONObject(responseBody);
      JSONObject firstDocument = jsonObject.getJSONArray("documents").getJSONObject(0);
      Double x = firstDocument.getDouble("x");
      Double y = firstDocument.getDouble("y");

      return new Coordinates(x, y);
    } else {
      System.out.println("실패");
      return new Coordinates(126.98163857, 37.47655999);
    }
  }

  public List<KakaoMapResponse> searchPlace(Member member) {

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "KakaoAK " + key);
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    Coordinates coordinates = convertAddressToCoordinates(member);

    String url =
        String.format(
            "%s?y=%f&x=%f&radius=%d&query=%s",
            uri, coordinates.y, coordinates.getX(), radius, query);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

    JSONObject jsonObject = new JSONObject(response.getBody());
    JSONArray documentsArray = jsonObject.getJSONArray("documents");

    List<KakaoMapResponse> results = new ArrayList<>();

    for (int i = 0; i < documentsArray.length(); i++) {

      JSONObject documentObject = documentsArray.getJSONObject(i);

      KakaoMapResponse kakaoMapResponse =
          KakaoMapResponse.builder()
              .place_name(documentObject.getString("place_name"))
              .distance(documentObject.getString("distance"))
              .place_url(documentObject.getString("place_url"))
              .address_name(documentObject.getString("address_name"))
              .road_address_name(documentObject.getString("road_address_name"))
              .x(documentObject.getString("x"))
              .y(documentObject.getString("y"))
              .phone(documentObject.optString("phone", ""))
              .build();

      results.add(kakaoMapResponse);
    }

    return results;
  }
}
