package com.example.capstone.service;

import com.example.capstone.domain.Disease;
import com.example.capstone.dto.request.DiseaseRequestDto.*;

public interface DiseaseCommandService {

  Disease createDisease(CreateDiseaseRequest request);
}
