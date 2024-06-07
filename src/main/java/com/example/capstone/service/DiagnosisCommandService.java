package com.example.capstone.service;

import com.example.capstone.domain.Diagnosis;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.DiagnosisRequestDto.*;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;

public interface DiagnosisCommandService {

  Diagnosis createDiagnosis(Member member, CreateDiagnosisRequest request, Long petId);

  void deleteDiagnosis(Member member, Long diagnosisId);

  GetDiagnosisResponse getDiagnosis(Long diagnosisId);
}
