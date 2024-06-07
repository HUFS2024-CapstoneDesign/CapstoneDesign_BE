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
}
