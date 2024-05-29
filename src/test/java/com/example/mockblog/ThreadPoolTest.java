package com.example.mockblog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootTest
public class ThreadPoolTest {
    @Test
    @Async("taskExecutor")
    public void testThreadTaskPool(){
        System.out.println("ThreadName: " + Thread.currentThread().getName());
    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void testRunTaskExecutor(){
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("ThreadName: " + Thread.currentThread().getName());
        });
    }

    @Test
    public void testRunTaskExecutorConfig(){
        Assertions.assertEquals(5, threadPoolTaskExecutor.getCorePoolSize());
    }
}
