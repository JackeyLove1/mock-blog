package com.example.mockblog.service.impl;

import com.example.mockblog.constants.RedisConstants;
import com.example.mockblog.service.CaptchaService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.CaptchaParam;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.UUID;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @return
     */
    @Override
    public CaptchaParam generateCode() {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        String result = specCaptcha.text();
        UUID key = UUID.randomUUID();
        redisTemplate.opsForValue().set(RedisConstants.LOGIN_CODE_KEY + key.toString(), result, RedisConstants.LOGIN_CODE_TTL, java.util.concurrent.TimeUnit.MINUTES);
        return new CaptchaParam(specCaptcha.toBase64(), key.toString(), null);
    }

    /**
     * @param key
     * @param answer
     * @return
     */
    @Override
    public Boolean verifyCode(String key, String answer) {
        String redisCode = redisTemplate.opsForValue().get(RedisConstants.LOGIN_CODE_KEY + key);
        if (redisCode == null || !redisCode.equals(answer)) {
            return false;
        }
        return true;
    }

    /**
     * 删除验证码
     * @param key 验证码Key
     */
    private void remove(String key){
        redisTemplate.opsForValue().getAndDelete(key);
    }
}
