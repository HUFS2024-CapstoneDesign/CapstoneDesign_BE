package com.example.capstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.capstone.common.BaseResponse;
import com.example.capstone.dto.request.DiseaseRequestDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.service.DiseaseCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/diseases")
@Tag(name = "\uD83E\uDD22 Disease", description = "질병 관련 API")
public class DiseaseController {

  private final DiseaseCommandService diseaseCommandService;

  @Operation(summary = "질병 생성 API", description = "질병을 생성합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<String> signUpMember(@RequestBody CreateDiseaseRequest request) {
    diseaseCommandService.createDisease(request);
    return BaseResponse.onSuccess(GlobalErrorCode.CREATED, "추가 완료");
  }
}
