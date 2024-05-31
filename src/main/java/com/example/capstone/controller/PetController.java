package com.example.capstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.capstone.Converter.PetConverter;
import com.example.capstone.annotation.auth.AuthMember;
import com.example.capstone.common.BaseResponse;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.PetRequestDto.*;
import com.example.capstone.dto.response.PetResponseDto.*;
import com.example.capstone.service.PetCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pets")
@Tag(name = "\uD83D\uDE3A Pet", description = "애완묘 관련 API")
public class PetController {

  private final PetCommandService petCommandService;

  @Operation(summary = "애완동물 등록 API", description = "애완동물을 등록합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<CreatePetResponse> createPet(
      @Parameter(hidden = true) @AuthMember Member member, @RequestBody CreatePetRequest request) {
    return BaseResponse.onSuccess(
        PetConverter.toCreatePetResponse(petCommandService.createPet(member, request)));
  }
}
