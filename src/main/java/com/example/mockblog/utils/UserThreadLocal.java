package com.example.mockblog.utils;

import com.example.mockblog.pojo.SysUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserThreadLocal {
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    public static SysUser get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
