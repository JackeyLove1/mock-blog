package com.example.mockblog.handler;

import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * DTO参数异常验证处理
     * @param e 异常类
     * @return 错误信息
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<String> methodArgumentNotValidException(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> messages = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), messages.getFirst());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> handleException(Exception ex){
        log.error("Internal Error");
        ex.printStackTrace();
        return Result.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMsg());
    }
}
