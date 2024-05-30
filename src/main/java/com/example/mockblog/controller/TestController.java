package com.example.mockblog.controller;

import com.example.mockblog.aop.Cache;
import com.example.mockblog.pojo.Article;
import com.example.mockblog.service.ArticleService;
import com.example.mockblog.vo.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @GetMapping
    public Result<String> test() {
        log.info("test");
        return Result.success("Test Succeed!");
    }

    @Autowired
    ArticleService articleService;

    @GetMapping("data/{id}")
    @Cacheable(value = "caffineCache", key = "#id")
    public Result<Article> getArticleById(@PathVariable Long id){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return articleService.findArticleById(id);
    }
}
