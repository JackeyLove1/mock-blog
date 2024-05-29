package com.example.mockblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

public interface RedisService {
    void set(String key, Object value);

    Object get(String key);

    void delete(String key);
}
