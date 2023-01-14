# exceptionHadler

→ API 개발 시 에러가 발생하는 경우 사용자에게 정의된 메세지를 반환할 수 있도록 에러를 정의 하여서 객체를 반환하는 방식

→ 에러 발생 시 사용자에게 원하는 내용만 전달 할 수 있음

```java
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
```

→ Null 에러 발생 시 ApiErrorResponse 객체 반환

**Case1) 정의 된 에러**

```java
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
```

결과

```json
{
    "status": 400,
    "message": "[ApiErrorResponse] Unauthorized"
}
```

**Case2) 정의 되지 않은 에러**

```java
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
```

결과

```java
{
    "timestamp": "2023-01-14T13:33:23.841+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/error2"
}
```

Case3) 정상 호출

```java
@GetMapping("/ok")
    public MemberDto ok(){

        return new MemberDto("test", new ApiErrorResponse(ApiErrorResponse.OK, ApiErrorResponse.OK_MSG));
    }
```

결과

```json
{
    "name": "test",
    "apiErrorResponse": {
        "status": 200,
        "message": "OK"
    }
}
```
