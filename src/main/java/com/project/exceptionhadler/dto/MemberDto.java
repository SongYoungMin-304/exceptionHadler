package com.project.exceptionhadler.dto;

import com.project.exceptionhadler.exception.ApiErrorResponse;
import lombok.Getter;

@Getter
public class MemberDto {

    private String name;

    private ApiErrorResponse apiErrorResponse;

    public MemberDto(String name, ApiErrorResponse apiErrorResponse) {
        this.name = name;
        this.apiErrorResponse = apiErrorResponse;
    }

}
