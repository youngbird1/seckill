package com.github.lyrric.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "success\n";
    }

}
