package com.example.capstone.service.Impl;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.capstone.Converter.DiagnosisConverter;
import com.example.capstone.Converter.DiseaseConverter;
import com.example.capstone.domain.Diagnosis;
import com.example.capstone.domain.Disease;
import com.example.capstone.domain.Pet;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.DiagnosisRequestDto.*;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.custom.MemberException;
import com.example.capstone.repository.DiagnosisRepository;
import com.example.capstone.repository.DiseaseRepository;
import com.example.capstone.repository.PetRepository;
import com.example.capstone.service.DiagnosisCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DiagnosisCommandServiceImpl implements DiagnosisCommandService {

  private final DiagnosisRepository diagnosisRepository;
  private final DiseaseRepository diseaseRepository;
  private final PetRepository petRepository;

  @Override
  public Diagnosis createDiagnosis(Member member, CreateDiagnosisRequest request, Long petId) {
    Disease disease =
        diseaseRepository
            .findByCode(request.getDiseaseCode())
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

    Pet pet =
        petRepository
            .findByIdAndMemberId(petId, member.getId())
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

    return diagnosisRepository.save(DiagnosisConverter.toDiagnosis(request, disease, pet));
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

  public Diagnosis createFakeDiagnosis(Member member, Long petId) {
    Optional<Disease> checkDisease = diseaseRepository.findByCode("01");

    if (checkDisease.isEmpty()) {
      Disease disease = diseaseRepository.save(DiseaseConverter.toFakeDisease());
    }

    Pet pet =
        petRepository
            .findByIdAndMemberId(petId, member.getId())
            .orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

    return diagnosisRepository.save(
        DiagnosisConverter.toFakeDiagnosis(diseaseRepository.findByCode("01").get(), pet));
  }
}
