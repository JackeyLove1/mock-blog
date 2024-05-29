package com.example.mockblog.mapper;

import com.example.mockblog.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 15727
* @description 针对表【ms_sys_user】的数据库操作Mapper
* @createDate 2024-05-28 16:17:05
* @Entity com.example.mockblog.pojo.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findByAccountAndPassword(String account, String password);

    SysUser findByAccount(String account);
}




