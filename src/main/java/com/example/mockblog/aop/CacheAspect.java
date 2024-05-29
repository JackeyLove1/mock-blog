package com.example.mockblog.aop;

import com.alibaba.fastjson2.JSON;
import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
@Slf4j
public class CacheAspect {
    private static final String CACHE_PREFIX = "cache:";

    @Pointcut("@annotation(com.example.mockblog.aop.Cache)")
    public void pt() {
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("pt()")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Signature signature = joinPoint.getSignature();
            String className = signature.getClass().getSimpleName();
            String methodName = signature.getName();

            Class[] parameterTypes = new Class[joinPoint.getArgs().length];
            Object[] args = joinPoint.getArgs();
            String params = "";
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params += JSON.toJSONString(args[i]);
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }
            if (StringUtils.isNoneBlank(params)) {
                params = DigestUtils.md5Hex(params);
            }
            var method = joinPoint.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            Cache annotation = method.getAnnotation(Cache.class);
            Long expire = annotation.expire();
            String name = annotation.name();
            String redisKey = CACHE_PREFIX + name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);

            if (StringUtils.isNotEmpty(redisValue)) {
                log.info("Cache hit key:{}", redisKey);
                return JSON.parseObject(redisValue, method.getReturnType());
            }
            Object result = joinPoint.proceed();
            String resultString = JSON.toJSONString(result);
            redisTemplate.opsForValue().set(redisKey, resultString, Duration.ofMillis(expire));
            log.info("Cache put key: {}, value: {}", redisKey, resultString);
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Result.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMsg());
    }
}
