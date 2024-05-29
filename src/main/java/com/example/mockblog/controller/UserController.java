package com.example.mockblog.controller;

import com.example.mockblog.pojo.SysUser;
import com.example.mockblog.service.LoginService;
import com.example.mockblog.service.SysUserService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LoginService loginService;

    @GetMapping("users/currentUser")
    public Result<SysUser> CurrentUser(@RequestHeader("Authorization") String token){
        return sysUserService.findUserByToken(token);
    }

    @PostMapping("login")
    public Result<String> Login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<String> Logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }

    @PostMapping("register")
    public Result<SysUser> Register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }
}
