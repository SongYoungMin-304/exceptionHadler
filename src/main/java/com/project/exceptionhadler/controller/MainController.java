package com.project.exceptionhadler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test")
    public String test(){

        if(1 == 1){
            throw new NullPointerException();
        }

        return "test";
    }

}
