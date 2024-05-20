package com.example.capstone.exception.custom;

import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.GlobalException;

public class MemberException extends GlobalException {

  public MemberException(GlobalErrorCode errorCode) {
    super(errorCode);
  }
}
