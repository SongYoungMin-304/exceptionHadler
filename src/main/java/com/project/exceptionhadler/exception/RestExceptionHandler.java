package com.project.exceptionhadler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// @ControllerAdvice  // AOP를 적용해서 Controller 에 있는 예외를 잡아서 처리할 수 있음 //@ExceptionHandler
// @ResponseBody //
public class RestExceptionHandler {

    @ExceptionHandler(value = { NullPointerException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse clientAuthenticationException(Exception e) {
        String msg = e.getMessage(); // 기본 제공하는 msg 셋팅
        return new ApiErrorResponse(ApiErrorResponse.BAD_REQUEST, ApiErrorResponse.UNAUTHORIZED_MSG);
    }

}
