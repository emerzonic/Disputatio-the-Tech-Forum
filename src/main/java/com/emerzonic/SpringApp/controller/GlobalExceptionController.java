package com.emerzonic.SpringApp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(value = Exception.class)
  public String handleException(Exception exception){
    exception.printStackTrace();
    return "error";
  }
}

