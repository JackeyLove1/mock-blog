package com.example.mockblog.service;

import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.LoginParam;

public interface LoginService {
    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result<String> login(LoginParam loginParam);

    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result<String> logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result<SysUser> register(LoginParam loginParam);
}
