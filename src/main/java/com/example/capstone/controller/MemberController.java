package com.example.capstone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.capstone.Converter.MemberConverter;
import com.example.capstone.annotation.auth.AuthMember;
import com.example.capstone.common.BaseResponse;
import com.example.capstone.domain.member.Member;
import com.example.capstone.dto.request.MemberRequestDto.*;
import com.example.capstone.dto.response.MemberResponseDto.*;
import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.kakao.KakaoLoginParams;
import com.example.capstone.kakao.KakaoMapService;
import com.example.capstone.service.MemberCommandService;
import com.example.capstone.service.MemberQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
@Tag(name = "😎 Member", description = "사용자 관련 API")
public class MemberController {

  private final MemberCommandService memberCommandService;
  private final MemberQueryService memberQueryService;
  private final KakaoMapService kakaoMapService;

  @Operation(summary = "회원가입 API", description = "회원가입을 진행합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<SignUpMemberResponse> signUpMember(@RequestBody SignUpMemberRequest request) {
    return BaseResponse.onSuccess(
        GlobalErrorCode.CREATED,
        MemberConverter.toSignUpMemberResponse(memberCommandService.signUpMember(request)));
  }

  @Operation(summary = "로그인 API", description = "이메일, 비밀번호를 사용한 로그인을 진행합니다")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<TokenResponse> loginMember(@RequestBody LoginMemberRequest request) {
    return BaseResponse.onSuccess(GlobalErrorCode.CREATED, memberCommandService.login(request));
  }

  @Operation(summary = "카카오 API", description = "카카오로그인을 합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "성공"),
  })
  @PostMapping("/kakao")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<TokenResponse> loginKakao(@RequestBody KakaoLoginParams params) {
    return BaseResponse.onSuccess(
        MemberConverter.toKakaoLogin(memberCommandService.loginKakao(params)));
  }

  @Operation(summary = "reissue API", description = "토큰을 재발급합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @PostMapping("/reissue")
  public BaseResponse<TokenResponse> reissue(@RequestBody ReissueRequest request) {
    return BaseResponse.onSuccess(memberCommandService.reissue(request));
  }

  @Operation(summary = "아이디 찾기 API", description = "닉네임을 통해 아이디를 찾습니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @GetMapping("/find-id")
  public BaseResponse<FindEmailByNickNameResponse> reissue(@RequestParam String nickName) {
    return BaseResponse.onSuccess(
        MemberConverter.toFindEmailByNickNameResponse(
            memberQueryService.findMemberByNickName(nickName).get()));
  }

  @Operation(summary = "비밀번호 찾기 API", description = "이메일을 통해 아이디를 찾습니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @PostMapping("/find-password")
  public BaseResponse<Boolean> findPasswordByEmail(@RequestBody FindPasswordByEmailRequest request)
      throws Exception {
    return BaseResponse.onSuccess(memberCommandService.sendEmail(request));
  }

  @Operation(summary = "비밀번호 찾기 API", description = "이메일을 통해 아이디를 찾습니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @PostMapping("/check-code")
  public BaseResponse<String> checkCode(@RequestBody VerifyCodeRequest request) throws Exception {
    return BaseResponse.onSuccess(memberCommandService.isVerifiedNumber(request));
  }

  @Operation(summary = "비밀번호 변경 API", description = "새 비밀번호로 변경합니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @PutMapping("/change-password")
  public BaseResponse<ChangePasswordResponse> changePassword(
      @RequestHeader String token, @RequestBody ChangePasswordRequest request) throws Exception {
    return BaseResponse.onSuccess(
        MemberConverter.toChangePasswordResponse(
            memberCommandService.findPassword(token, request)));
  }

  @Operation(summary = "닉네임 중복 확인 API", description = "닉네임 중복을 확인합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @PostMapping("/check-nickname")
  public BaseResponse<Boolean> isDuplicatedNickName(
      @RequestBody IsDuplicatedNickNameRequest request) {
    return BaseResponse.onSuccess(
        !(memberQueryService.findMemberByNickName(request.getNickName()).isPresent()));
  }

  @Operation(summary = "이메일 중복 확인 API", description = "이메일 중복을 확인합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @PostMapping("/check-email")
  public BaseResponse<Boolean> isDuplicatedEmail(@RequestBody IsDuplicatedEmailRequest request) {
    return BaseResponse.onSuccess(
        !(memberQueryService.findMemberByNickName(request.getEmail()).isPresent()));
  }

  @Operation(summary = "주소 변경 API", description = "회원의 주소를 변경합니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @PutMapping("/set-address")
  public BaseResponse<SetAddressResponse> setAddress(
      @Parameter(hidden = true) @AuthMember Member member, @RequestBody SetAddressRequest request) {
    return BaseResponse.onSuccess(
        MemberConverter.toSetAddressResponse(memberCommandService.setAddress(member, request)));
  }

  @Operation(summary = "회원 탈퇴 API", description = "회원을 탈퇴시킵니다.")
  @ApiResponse(responseCode = "201", description = "성공")
  @DeleteMapping("/delete")
  public BaseResponse<String> deleteMember(@Parameter(hidden = true) @AuthMember Member member) {
    return BaseResponse.onSuccess(memberCommandService.deleteMember(member));
  }

  @Operation(summary = "병원 검색 API", description = "5km 이내의 병원을 검색합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  @GetMapping("/search")
  public BaseResponse<List<KakaoMapService.KakaoMapResponse>> searchPlaces(
      @Parameter(hidden = true) @AuthMember Member member) {
    return BaseResponse.onSuccess(kakaoMapService.searchPlace(member));
  }

  ///
}
