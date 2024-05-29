package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.pojo.Article;
import com.example.mockblog.service.ArticleService;
import com.example.mockblog.mapper.ArticleMapper;
import com.example.mockblog.service.ThreadService;
import com.example.mockblog.vo.Result;
import com.example.mockblog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author 15727
 * @description 针对表【ms_article】的数据库操作Service实现
 * @createDate 2024-05-28 14:43:42
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ThreadService threadService;

    @Override
    public Result<List<Article>> listArticlePage(PageParam pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        articleMapper.selectPage(page, queryWrapper);
        return Result.success(page.getRecords());
    }

    @Override
    public Result<List<Article>> hotArticle(){
        final Integer limit = 5;
        return Result.success(articleMapper.hotArticle(limit));
    }

    @Override
    public Result<List<Article>> newArticle(){
        final Integer limit = 5;
        return Result.success(articleMapper.newArticle(limit));
    }

    @Override
    public Result<Article> findArticleById(Long articleId){
        Article article = articleMapper.selectById(articleId);
        if(article == null){
            return Result.success(null);
        }
        threadService.updateViewCount(articleId, article.getViewCounts());
        return Result.success(article);
    }
}




