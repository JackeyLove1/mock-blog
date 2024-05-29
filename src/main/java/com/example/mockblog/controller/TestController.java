package com.example.mockblog.controller;

import com.example.mockblog.vo.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @GetMapping
    public Result<String> test() {
        log.info("test");
        return Result.success("Test Succeed!");
    }
}
