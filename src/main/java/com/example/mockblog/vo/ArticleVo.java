package com.example.mockblog.vo;

import lombok.Data;

@Data
public class ArticleVo {
    private String id;
    private String title;
    private String summary;
    private Integer commentCounts;
    private Integer viewCounts;
    private Integer weight;
    private String createDate;
    private String author;
    private String categorys;
}
