package com.example.mockblog.service;

import com.example.mockblog.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 15727
* @description 针对表【ms_sys_user】的数据库操作Service
* @createDate 2024-05-28 16:17:05
*/
public interface SysUserService extends IService<SysUser> {
    public SysUser findSysUserById(Long userId);
}
