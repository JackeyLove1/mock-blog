package com.example.mockblog;

import com.example.mockblog.service.impl.RedisService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    private final Faker faker = new Faker();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisSetGetKey(){
        final String key = "test:user:1";
        final String value = "{\"id\":1,\"name\":\"jacky\",\"age\":18}";
        redisTemplate.opsForValue().set(key, value);
        String getValue = redisTemplate.opsForValue().get(key);
        System.out.println(getValue);
        Assertions.assertEquals(value, getValue);
    }


    @Autowired
    private RedisService redisService;

    @Test
    void testRedisService(){
        final String key = faker.name().username();
        final String value = faker.name().fullName();

        redisService.setCacheObject(key, value);
        final String getValue = redisService.getCacheObject(key);
        Assertions.assertEquals(value, getValue);
        redisService.deleteObject(key);
        Assertions.assertNull(redisService.getCacheObject(key));
    }
}
