package com.example.mockblog.aop;

import com.alibaba.fastjson2.JSON;
import com.example.mockblog.utils.HttpContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.example.mockblog.aop.LogAnnotation)")
    public void pt() {
    }

    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long cost = System.currentTimeMillis() - begin;
        recordLog(joinPoint, cost);
        return result;
    }

    public void recordLog(ProceedingJoinPoint joinPoint, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module: {}", annotation.module());
        log.info("operation: {}", annotation.operation());

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method: {}", className + "." + methodName + "()");

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params: {}", params);

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip: {}", request.getRemoteAddr());

        log.info("excute time : {} ms", time);
        log.info("=====================log end================================");
    }
}
