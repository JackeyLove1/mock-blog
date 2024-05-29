package com.example.mockblog;

import com.example.mockblog.service.RedisService;
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
    public void testRedisService(){
        String key = faker.name().name();
        String value = faker.internet().password();
        redisService.set(key, value);
        Assertions.assertEquals(value, (String)redisService.get(key));
        redisService.delete(key);
        Assertions.assertNull(redisService.get(key));
    }
}
