package com.example.mockblog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mockblog.mapper.SysUserMapper;
import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.service.SysUserService;
import com.example.mockblog.utils.MockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelect(){
        sysUserMapper.selectList(null)
                .stream()
                .forEach(System.out::println);

        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getId, 1L);
        SysUser user = sysUserMapper.selectOne(lambdaQueryWrapper);
        Assertions.assertNotNull(user);
        System.out.println(user);
        Assertions.assertEquals(1L, user.getId());
    }

    @Test
    public void testInsertOne(){
        SysUser user = MockUtils.mockSysUser();
        sysUserMapper.insert(user);
        Assertions.assertNotNull(user.getId());
        System.out.println(user);
    }

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testServiceFind(){
        Long userId = 1L;
        SysUser user = sysUserService.findSysUserById(userId);
        Assertions.assertNotNull(user);
        System.out.println(user);
    }
}
