package com.example.mockblog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.mockblog.config.LoginConfig;
import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.service.LoginService;
import com.example.mockblog.service.SysUserService;
import com.example.mockblog.utils.JwtUtils;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LoginConfig loginConfig;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public SysUser checkToken(String token) {
        return jwtUtils.checkAndParseUserToken(token);
    }

    @Override
    public Result<String> login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        password = DigestUtils.md5Hex(password + loginConfig.getSalt());
        SysUser user = sysUserService.findByAccountAndPassword(account, password);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = jwtUtils.generateToken(user.getId());
        redisTemplate.opsForValue().set(jwtUtils.getJwtConfig().getPrefix() + ":" + token, JSON.toJSONString(user));
        return Result.success(token);
    }

    @Override
    public Result<String> logout(String token) {
        redisTemplate.delete(jwtUtils.getJwtConfig().getPrefix() + ":" + token);
        return Result.success();
    }

    @Override
    public Result<SysUser> register(LoginParam loginParam) {
        /**
         * 1. 判断参数 是否合法
         * 2. 判断账户是否存在，存在 返回账户已经被注册
         * 3. 不存在，注册用户
         * 4. 生成token
         * 5. 存入redis 并返回
         * 6. 注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
            || StringUtils.isBlank(password)
            || StringUtils.isBlank(nickname)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        SysUser sysUser = sysUserService.findByAccount(account);
        if (sysUser != null) {
            return Result.fail(ErrorCode.USER_ACCOUNT_EXISTED.getCode(), ErrorCode.USER_ACCOUNT_EXISTED.getMsg());
        }
        SysUser user = new SysUser();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password + loginConfig.getSalt()));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        sysUserService.save(user);
        String token = jwtUtils.generateToken(user.getId());
        redisTemplate.opsForValue().set(jwtUtils.getJwtConfig().getPrefix() + ":" + token, JSON.toJSONString(user));
        return Result.success(user);
    }
}
