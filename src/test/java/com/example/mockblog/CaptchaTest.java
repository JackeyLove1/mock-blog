package com.example.mockblog;

import com.example.mockblog.service.CaptchaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaptchaTest {
    @Autowired
    private CaptchaService captchaService;

    @Test
    public void testCaptchaBasic() {
        var result = captchaService.generateCode();
        System.out.println(result);
    }
}
