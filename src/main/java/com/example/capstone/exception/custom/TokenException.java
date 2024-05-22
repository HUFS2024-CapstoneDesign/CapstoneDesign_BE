package com.example.capstone.exception.custom;

import com.example.capstone.exception.GlobalErrorCode;
import com.example.capstone.exception.GlobalException;

public class TokenException extends GlobalException {
  public TokenException(GlobalErrorCode errorCode) {
    super(errorCode);
  }
}
