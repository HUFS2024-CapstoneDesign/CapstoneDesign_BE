package com.example.capstone.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {
  CREATED(HttpStatus.CREATED, "요청 성공 및 리소스 생성됨"),
  UPDATED(HttpStatus.ACCEPTED, "요청 성공 및 리소스 수정됨"),
  DELETED(HttpStatus.NO_CONTENT, "요청 성공 및 리소스 삭제됨"),

  // member
  NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 영문, 숫자, 특수문자를 포함한 9~16글자여야 합니다."),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
  AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록된 사용자가 없습니다."),
  PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰입니다."),
  FAIL_TO_SEND(HttpStatus.CONFLICT, "메일 보내기를 실패하였습니다."),
  NUMBER_NOT_MATCH(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않습니다."),
  DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
