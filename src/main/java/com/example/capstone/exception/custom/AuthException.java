package com.example.capstone.exception.custom;

import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.GlobalException;

public class AuthException extends GlobalException {

  public AuthException(GlobalErrorCode errorCode) {
    super(errorCode);
  }
}
