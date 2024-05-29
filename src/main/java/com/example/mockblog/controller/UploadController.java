package com.example.mockblog.controller;

import com.example.mockblog.errors.ErrorCode;
import com.example.mockblog.utils.QiniuUtils;
import com.example.mockblog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        Boolean uploadResult = qiniuUtils.upload(file, fileName);
        if (uploadResult) {
            return Result.success(qiniuUtils.getUrl() + fileName);
        }
        return Result.fail(ErrorCode.UPLOAD_ERROR.getCode(), ErrorCode.UPLOAD_ERROR.getMsg());
    }
}
