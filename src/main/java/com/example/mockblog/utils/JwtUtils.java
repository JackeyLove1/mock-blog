package com.example.mockblog.utils;

import com.alibaba.fastjson2.JSON;
import com.example.mockblog.config.JwtConfig;
import com.example.mockblog.pojo.SysUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class JwtUtils {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        // Decode the Base64 encoded key
        byte[] decodedKey = Base64.getDecoder().decode(jwtConfig.getSecret());
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .setClaims(claims)
                .compact();
        return token;
    }

    public Long getUserId(String token) {
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.get("userId").toString());
    }

    public Map<String, Object> checkToken(String token) {
        try {
            Map<String, Object> claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public SysUser checkAndParseUserToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> claims = checkToken(token);
        if (claims == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get(jwtConfig.getPrefix() + ":" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        SysUser user = JSON.parseObject(userJson, SysUser.class);
        return user;
    }
}
