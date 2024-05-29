package com.example.mockblog;

import com.example.mockblog.config.JwtConfig;
import com.example.mockblog.utils.JwtUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Random;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JwtUtils jwtUtils;

    private final Random random = new Random();

    @Test
    public void testSecretValid() {
        String s1 = jwtUtils.getJwtConfig().getSecret();
        String s2 = jwtConfig.getSecret();
        System.out.println("secret: " + s1);
        Assertions.assertNotNull(s1);
        Assertions.assertTrue(StringUtils.isNotBlank(s1));
        Assertions.assertEquals(s1, s2);
    }

    @Test
    public void testJwtBasic(){
        Long userId = random.nextLong();
        String token = jwtUtils.generateToken(userId);
        Assertions.assertNotNull(token);
        Map<String, Object> claims = jwtUtils.checkToken(token);
        Assertions.assertNotNull(claims);
        Assertions.assertEquals(userId, jwtUtils.getUserId(token));
    }
}
