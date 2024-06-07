package com.example.capstone.Converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.capstone.domain.Diagnosis;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;

@Component
public class DiagnosisConverter {

  public static DiagnosisResponse toDiagnosisResponse(Diagnosis diagnosis) {
    return DiagnosisResponse.builder()
        .disease(diagnosis.getDisease().toString())
        .createAt(diagnosis.getCreatedAt())
        .build();
  }

  public static List<DiagnosisResponse> toDiagnosisResponseList(List<Diagnosis> diagnosisList) {
    return diagnosisList.stream()
        .map(diagnosis -> DiagnosisConverter.toDiagnosisResponse(diagnosis))
        .toList();
  }
}
