package com.github.lyrric.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        log.info("111111111");
        log.info("222222222");
        return "success\n";
    }

}
