package com.example.mockblog;

import com.example.mockblog.aop.LogAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {
    @Test
    @LogAnnotation(module = "test", operation = "test")
    public void testLogAnnotation() {
        System.out.println("test");
    }
}
