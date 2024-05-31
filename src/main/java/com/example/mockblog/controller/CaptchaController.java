package com.example.mockblog.controller;

import com.example.mockblog.aop.LogAnnotation;
import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.service.CaptchaService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.CaptchaParam;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @LogAnnotation(module = "captcha", operation = "generateCode")
    @RequestMapping(value = "code", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<CaptchaParam> generateCode() {
        return Result.success(captchaService.generateCode());
    }

    @LogAnnotation(module = "captcha", operation = "validateCode")
    @GetMapping("validate")
    public Result<String> verifyCode(@Valid @RequestBody CaptchaParam captchaParam) {
        log.info("captchaParam: {}", captchaParam.toString());
        return captchaService.verifyCode(captchaParam.getCode(), captchaParam.getAnswer()) ? Result.success("验证码正确") : Result.fail(ErrorCode.CAPTCHA_ERROR.getCode(), ErrorCode.CAPTCHA_ERROR.getMsg());
    }
}
