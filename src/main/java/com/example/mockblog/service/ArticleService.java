package com.example.mockblog.service;

import com.example.mockblog.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.PageParam;

import java.util.List;

/**
* @author 15727
* @description 针对表【ms_article】的数据库操作Service
* @createDate 2024-05-28 14:43:42
*/
public interface ArticleService extends IService<Article> {
    Result<List<Article>> listArticlePage(PageParam pageParams);

    Result<List<Article>> hotArticle();

    Result<List<Article>> newArticle();

    Result<Article> findArticleById(Long articleId);
}
