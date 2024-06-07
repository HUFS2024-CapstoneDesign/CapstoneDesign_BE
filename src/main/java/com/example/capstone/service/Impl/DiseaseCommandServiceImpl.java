package com.example.capstone.service.Impl;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.capstone.Converter.DiseaseConverter;
import com.example.capstone.domain.Disease;
import com.example.capstone.dto.request.DiseaseRequestDto.*;
import com.example.capstone.repository.DiseaseRepository;
import com.example.capstone.service.DiseaseCommandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DiseaseCommandServiceImpl implements DiseaseCommandService {

  private final DiseaseRepository diseaseRepository;

  @Override
  public Disease createDisease(CreateDiseaseRequest request) {
    return diseaseRepository.save(DiseaseConverter.toDisease(request));
  }
}
