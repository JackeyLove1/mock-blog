package com.example.mockblog.errors;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public enum ErrorCode {
    SUCCESS(200, "success"),
    PARAMS_ERROR(400, "参数错误"),
    NOT_FOUND_ERROR(404, "资源不存在"),
    SERVER_ERROR(500, "服务器错误"),
    USER_ACCOUNT_EXISTED(10001, "账号已存在"),
    USER_ACCOUNT_NOT_EXIST(10002, "账号不存在"),
    USER_PWD_NOT_CORRECT(10003, "密码不正确"),
    USER_NOT_LOGIN(10004, "用户未登录");

    private final Integer code;
    private final String msg;

}