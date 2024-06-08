package com.example.capstone.Converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.capstone.domain.Diagnosis;
import com.example.capstone.domain.Disease;
import com.example.capstone.domain.Pet;
import com.example.capstone.dto.request.DiagnosisRequestDto.*;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;

@Component
public class DiagnosisConverter {

  public static DiagnosisResponse toDiagnosisResponse(Diagnosis diagnosis) {
    return DiagnosisResponse.builder()
        .diseaseName(diagnosis.getDisease().getName())
        .createdAt(diagnosis.getCreatedAt())
        .build();
  }

  public static List<DiagnosisResponse> toDiagnosisResponseList(List<Diagnosis> diagnosisList) {
    return diagnosisList.stream()
        .map(diagnosis -> DiagnosisConverter.toDiagnosisResponse(diagnosis))
        .toList();
  }

  public static Diagnosis toDiagnosis(CreateDiagnosisRequest request, Disease disease, Pet pet) {
    return Diagnosis.builder()
        .medicalExpense(request.getMedicalExpense())
        .disease(disease)
        .pet(pet)
        .build();
  }

  public static Diagnosis toFakeDiagnosis(Disease disease, Pet pet) {
    return Diagnosis.builder().medicalExpense(103000).disease(disease).pet(pet).build();
  }

  public static CreateDiagnosisResponse toCreateDiagnosisResponse(Diagnosis diagnosis) {
    return CreateDiagnosisResponse.builder()
        .id(diagnosis.getId())
        .medicalExpense(diagnosis.getMedicalExpense())
        .petName(diagnosis.getPet().getName())
        .diseaseName(diagnosis.getDisease().getName())
        .createdAt(diagnosis.getCreatedAt())
        .build();
  }

  public static GetDiagnosisResponse toGetDiagnosisResponse(Diagnosis diagnosis) {
    return GetDiagnosisResponse.builder()
        .petId(diagnosis.getPet().getId())
        .petName(diagnosis.getPet().getName())
        .diseaseName(diagnosis.getDisease().getName())
        .explanation(diagnosis.getDisease().getExplanation())
        .symptom1(diagnosis.getDisease().getSymptom1())
        .symptom2(diagnosis.getDisease().getSymptom2())
        .symptom3(diagnosis.getDisease().getSymptom3())
        .cause(diagnosis.getDisease().getCauses())
        .medicalExpense(diagnosis.getMedicalExpense())
        .createdAt(diagnosis.getCreatedAt())
        .build();
  }
}
