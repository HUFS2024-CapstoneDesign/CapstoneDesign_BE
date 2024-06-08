package com.example.capstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.capstone.Converter.DiagnosisConverter;
import com.example.capstone.annotation.auth.AuthMember;
import com.example.capstone.common.BaseResponse;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.DiagnosisRequestDto.*;
import com.example.capstone.dto.response.DiagnosisResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.service.DiagnosisCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/diagnosis")
@Tag(name = "\uD83D\uDCC3 Diagnosis", description = "진단 관련 API")
public class DiagnosisController {

  private final DiagnosisCommandService diagnosisCommandService;

  @Operation(summary = "진단 생성 API", description = "진단을 생성합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/{petId}")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<CreateDiagnosisResponse> createDiagnosis(
      @Parameter(hidden = true) @AuthMember Member member,
      @RequestBody CreateDiagnosisRequest request,
      @RequestParam(name = "petId") Long petId) {
    return BaseResponse.onSuccess(
        GlobalErrorCode.CREATED,
        DiagnosisConverter.toCreateDiagnosisResponse(
            diagnosisCommandService.createDiagnosis(member, request, petId)));
  }

  @Operation(summary = "진단 삭제 API", description = "진단을 삭제합니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @DeleteMapping("/delete/{diagnosisId}")
  public BaseResponse<String> deleteDiagnosis(
      @Parameter(hidden = true) @AuthMember Member member,
      @RequestParam(name = "diagnosisId") Long diagnosisId) {
    diagnosisCommandService.deleteDiagnosis(member, diagnosisId);
    return BaseResponse.onSuccess("진단 삭제");
  }

  @Operation(summary = "진단 조회 API", description = "진단을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @GetMapping("/{diagnosisId}")
  public BaseResponse<GetDiagnosisResponse> getDiagnosis(
      @Parameter(hidden = true) @AuthMember Member member,
      @RequestParam(name = "diagnosisId") Long diagnosisId) {
    return BaseResponse.onSuccess(diagnosisCommandService.getDiagnosis(diagnosisId));
  }

  @Operation(summary = "가짜 진단 생성 API", description = "가짜 진단을 생성합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("fake/{petId}")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<CreateDiagnosisResponse> createFakeDiagnosis(
      @Parameter(hidden = true) @AuthMember Member member,
      @RequestParam(name = "petId") Long petId) {
    return BaseResponse.onSuccess(
        GlobalErrorCode.CREATED,
        DiagnosisConverter.toCreateDiagnosisResponse(
            diagnosisCommandService.createFakeDiagnosis(member, petId)));
  }
}
