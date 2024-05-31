package com.example.mockblog.service;

import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.CaptchaParam;

public interface CaptchaService {
    CaptchaParam generateCode();

    Boolean verifyCode(String key, String answer);
}
