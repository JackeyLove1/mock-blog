package com.example.mockblog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<T>(false, 200, "success", null);
    }

    public static <T> Result<T> fail() {
        return new Result<T>(false, 200, "fail", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, 200, "success", data);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<>(false, code, msg, null);
    }
}
