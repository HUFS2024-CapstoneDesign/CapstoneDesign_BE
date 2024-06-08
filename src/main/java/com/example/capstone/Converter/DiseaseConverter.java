package com.example.capstone.Converter;

import org.springframework.stereotype.Component;

import com.example.capstone.domain.Disease;
import com.example.capstone.dto.request.DiseaseRequestDto.*;

@Component
public class DiseaseConverter {

  public static Disease toDisease(CreateDiseaseRequest request) {
    return Disease.builder()
        .code(request.getCode())
        .name(request.getName())
        .explanation(request.getExplanation())
        .symptom1(request.getSymptom1())
        .symptom2(request.getSymptom2())
        .symptom3(request.getSymptom3())
        .causes(request.getCauses())
        .build();
  }

  public static Disease toFakeDisease() {
    return Disease.builder()
        .code("01")
        .name("결막염")
        .explanation("결막염은 가장 대표적인 고양이 눈병 중 하나인데요. 결막염은 번지거나 악화되는 속도가 빨라 초기에 빠르게 치료하는 것이 중요합니다.")
        .symptom1("눈을 뜨는 것을 불편해함")
        .symptom2("흰자위 부분 빨갛게 충혈")
        .symptom3("눈 주변이 부어있음")
        .causes(
            "결막염은 세균, 바이러스, 곰팡이, 기생충 등에 감염되어 발생하는 경우가 가장 흔하다. 알레르기가 있거나 눈에 속눈썹 등의 이물질이 눈에 들어가는 경우에도 발생할 수 있다. 또 눈꺼풀 종양이 있는 경우에도 마찰로 자극이 되어 결막염이 생길 수 있다.")
        .build();
  }
}
