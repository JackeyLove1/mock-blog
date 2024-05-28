package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.service.SysUserService;
import com.example.mockblog.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 15727
* @description 针对表【ms_sys_user】的数据库操作Service实现
* @createDate 2024-05-28 16:17:05
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserById(Long userId){
        return sysUserMapper.selectById(userId);
    }
}




