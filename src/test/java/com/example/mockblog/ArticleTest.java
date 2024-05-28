package com.example.mockblog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mockblog.mapper.ArticleMapper;
import com.example.mockblog.pojo.Article;
import com.example.mockblog.service.ArticleService;
import com.example.mockblog.vo.params.PageParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Test
    void testSelectOneArticle() {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, 1);
        Article article = articleMapper.selectOne(lambdaQueryWrapper);
        System.out.println(article);
    }

    @Test
    void testSelectAllArticles() {
        List<Article> list = articleMapper.selectList(null);
        list.stream().forEach(System.out::println);
    }

    @Test
    void testArticlePage() {
        Page<Article> page = new Page<>(1, 10);
        articleMapper.selectPage(page, null);
        page.getRecords()
                .stream()
                .forEach(System.out::println);
        System.out.println("Counts: " + page.getTotal());
    }

    @Test
    void testArticlePageService() {
        PageParams params = new PageParams();
        var result = articleService.listArticlePage(params);
        List<Article> list = result.getData();
        list.stream().forEach(System.out::println);

        params.setPage(1);
        params.setPageSize(10);
        result = articleService.listArticlePage(params);
        List<Article> list2 = result.getData();
        list2.stream().forEach(System.out::println);

        Assertions.assertEquals(list, list2);
    }
}
