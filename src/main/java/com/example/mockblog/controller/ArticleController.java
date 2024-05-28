package com.example.mockblog.controller;

import com.example.mockblog.pojo.Article;
import com.example.mockblog.service.ArticleService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<List<Article>> listArticle(@RequestBody PageParams params){
        return articleService.listArticlePage(params);
    }

}
