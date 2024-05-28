package com.example.mockblog.utils;

import com.example.mockblog.pojo.SysUser;
import com.github.javafaker.Faker;

public class MockUtils {
    static final Faker faker = new Faker();
    static public SysUser mockSysUser() {
        SysUser sysUser = new SysUser();
        sysUser.setAdmin(false);
        sysUser.setAccount(faker.lorem().characters(10));
        sysUser.setEmail(faker.internet().emailAddress());
        sysUser.setAvatar(faker.internet().avatar());
        sysUser.setMobilePhoneNumber(faker.phoneNumber().cellPhone());
        sysUser.setSalt(faker.lorem().characters(10));
        sysUser.setPassword(faker.lorem().characters(10));
        return sysUser;
    }
}
