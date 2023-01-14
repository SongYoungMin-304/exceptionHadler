package com.project.exceptionhadler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// @ControllerAdvice  // AOP를 적용해서 Controller 에 있는 예외를 잡아서 처리할 수 있음 //@ExceptionHandler
// @ResponseBody // 반환값을 자바 객체로 전환
public class RestExceptionHandler {

    /*
     * NullPointer 에러 발생 시 정의된 에러르 반환
     */
    @ExceptionHandler(value = { NullPointerException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse clientAuthenticationException(Exception e) {
        String msg = e.getMessage(); // 기본 제공하는 msg 셋팅
        log.error("error Check "+msg);
        log.error("error Check "+e);
        e.printStackTrace();

        return new ApiErrorResponse(ApiErrorResponse.BAD_REQUEST, ApiErrorResponse.UNAUTHORIZED_MSG);
    }

}
