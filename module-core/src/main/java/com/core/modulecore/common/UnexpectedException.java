package com.core.modulecore.common;

public class UnexpectedException extends RuntimeException {
  public UnexpectedException(String message, Throwable cause) {
    super(message, cause);
  }
}
