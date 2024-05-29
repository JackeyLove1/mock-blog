package com.example.mockblog.service;

import com.example.mockblog.mapper.ArticleMapper;
import com.example.mockblog.pojo.Article;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void initViewCount() {
        List<Article> articles = articleMapper.hotArticle(100);
        for (Article article : articles) {
            redisTemplate.opsForHash().put("viewCount", article.getId().toString(), article.getViewCounts().toString());
        }
    }

    @Async("taskExecutor")
    public void updateViewCount(Long articleId, Integer viewCounts) {
        if (redisTemplate.opsForHash().get("viewCount", articleId.toString()) == null){
            redisTemplate.opsForHash().put("viewCount", articleId.toString(), viewCounts.toString());
        }
        redisTemplate.opsForHash().increment("viewCount", articleId.toString(), 1);
    }
}
