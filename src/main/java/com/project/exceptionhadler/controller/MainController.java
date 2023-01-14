package com.project.exceptionhadler.controller;

import com.project.exceptionhadler.dto.MemberDto;
import com.project.exceptionhadler.exception.ApiErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    /*
     * NullPointer 에러 발생
     * RestExceptionHandler 에 정의되어 있는 에러로 에러 발생 시 수집됨
     */
    @GetMapping("/error1")
    public MemberDto error1(){

        if(1 == 1){
            throw new NullPointerException();
        }
        return new MemberDto("test", new ApiErrorResponse(ApiErrorResponse.OK, ApiErrorResponse.OK_MSG));
    }

    /*
     * NumberFormatException 에러 발생
     * RestExceptionHandler 에 정의되어있지 않은 에러
     */
    @GetMapping("/error2")
    public MemberDto error2(){

        if(1 == 1){
            throw new NumberFormatException();
        }
        return new MemberDto("test", new ApiErrorResponse(ApiErrorResponse.OK, ApiErrorResponse.OK_MSG));
    }

    /*
     * 정상 호출
     */
    @GetMapping("/ok")
    public MemberDto ok(){

        return new MemberDto("test", new ApiErrorResponse(ApiErrorResponse.OK, ApiErrorResponse.OK_MSG));
    }

}
