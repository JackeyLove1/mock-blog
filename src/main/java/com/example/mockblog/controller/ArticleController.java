package com.example.mockblog.controller;

import com.example.mockblog.aop.Cache;
import com.example.mockblog.pojo.Article;
import com.example.mockblog.service.ArticleService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<List<Article>> listArticle(@RequestBody PageParam params){
        return articleService.listArticlePage(params);
    }

    @RequestMapping(value = "hot", method = {RequestMethod.GET, RequestMethod.POST})
    @Cache(expire = 1000 * 60 * 10, name = "hot-articles")
    public Result<List<Article>> hotArticle(){
        return articleService.hotArticle();
    }

    @RequestMapping(value = "new", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<List<Article>> newArticle(){
        return articleService.newArticle();
    }

    @PostMapping("view/{id}")
    @Cache(expire = 1000 * 60 * 10, name = "view-article")
    public Result<Article> viewArticle(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

}
