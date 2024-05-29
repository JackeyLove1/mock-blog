package com.example.mockblog.service;

import com.example.mockblog.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mockblog.vo.Result;

/**
* @author 15727
* @description 针对表【ms_sys_user】的数据库操作Service
* @createDate 2024-05-28 16:17:05
*/
public interface SysUserService extends IService<SysUser> {
    SysUser findSysUserById(Long userId);

    SysUser findByAccountAndPassword(String account, String password);

    SysUser findByAccount(String account);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result<SysUser> findUserByToken(String token);
}
