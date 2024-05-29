package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.service.LoginService;
import com.example.mockblog.service.SysUserService;
import com.example.mockblog.mapper.SysUserMapper;
import com.example.mockblog.utils.JwtUtils;
import com.example.mockblog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public SysUser findSysUserById(Long userId){
        return sysUserMapper.selectById(userId);
    }

    @Override
    public SysUser findByAccount(String account) {
        return sysUserMapper.findByAccount(account);
    }

    @Override
    public SysUser findByAccountAndPassword(String account, String password) {
        return sysUserMapper.findByAccountAndPassword(account, password);
    }

    @Override
    public Result<SysUser> findUserByToken(String token) {
        /**
         * 1. token合法性校验
         *    是否为空，解析是否成功 redis是否存在
         * 2. 如果校验失败 返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */
        SysUser user = jwtUtils.checkAndParseUserToken(token);
        if (user == null){
            return Result.fail(ErrorCode.USER_NOT_LOGIN.getCode(), ErrorCode.USER_NOT_LOGIN.getMsg());
        }
        return Result.success(user);
    }
}




