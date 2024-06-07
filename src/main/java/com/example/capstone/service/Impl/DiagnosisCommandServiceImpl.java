package com.example.capstone.service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.capstone.Converter.DiagnosisConverter;
import com.example.capstone.domain.Diagnosis;
import com.example.capstone.domain.Disease;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.DiagnosisRequestDto.*;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.repository.DiagnosisRepository;
import com.example.capstone.repository.DiseaseRepository;
import com.example.capstone.service.DiagnosisCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DiagnosisCommandServiceImpl implements DiagnosisCommandService {

  private final DiagnosisRepository diagnosisRepository;
  private final DiseaseRepository diseaseRepository;

  @Override
  public Diagnosis createDiagnosis(Member member, CreateDiagnosisRequest request) {
    Disease disease =
        diseaseRepository
            .findByCode(request.getDiseaseCode())
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));
    return diagnosisRepository.save(DiagnosisConverter.toDiagnosis(request, disease, member));
  }

  @Override
  public void deleteDiagnosis(Member member, Long diagnosisId) {
    Diagnosis diagnosis =
        diagnosisRepository
            .findById(diagnosisId)
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));
    diagnosisRepository.delete(diagnosis);
  }

  @Override
  public GetDiagnosisResponse getDiagnosis(Long diagnosisId) {
    return DiagnosisConverter.toGetDiagnosisResponse(
        diagnosisRepository
            .findById(diagnosisId)
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND)));
  }
}
