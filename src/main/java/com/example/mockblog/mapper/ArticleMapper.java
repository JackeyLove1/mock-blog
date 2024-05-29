package com.example.mockblog.mapper;

import com.example.mockblog.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 15727
* @description 针对表【ms_article】的数据库操作Mapper
* @createDate 2024-05-28 14:43:42
* @Entity com.example.mockblog.pojo.Article
*/

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    public List<Article> hotArticle(Integer limit);

    public List<Article> newArticle(Integer limit);

    public void updateViewCountsById(Long articleId, Integer viewCounts);
}




