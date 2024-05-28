package com.example.mockblog;

import com.example.mockblog.mapper.TagMapper;
import com.example.mockblog.pojo.Tag;
import com.example.mockblog.service.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagTest {
    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void testFindTagsByArticleId() {
        final Long articleId = 1L;
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        Assertions.assertNotEquals(tags.size(), 0);
        tags.stream().forEach(System.out::println);
    }

    @Test
    public void testFindHotTags() {
        final Integer limit = 1;
        List<Long> lists = tagMapper.findHotsTagIds(limit);
        lists.stream().forEach(System.out::println);
    }

    @Test
    public void testFindTagsByTagIds() {
        final List<Long> tagIds = List.of(5L, 6L, 7L, 8L);
        List<Tag> tags = tagMapper.findTagsByTagIds(tagIds);
        tags.stream().forEach(System.out::println);
    }
}
