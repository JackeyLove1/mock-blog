package com.example.mockblog.mapper;

import com.example.mockblog.pojo.Article;
import com.example.mockblog.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 15727
 * @description 针对表【ms_tag】的数据库操作Mapper
 * @createDate 2024-05-28 16:17:11
 * @Entity com.example.mockblog.pojo.Tag
 */
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    public List<Tag> findTagsByArticleId(@Param("id") Long articleId);

    /**
     * 查询最热的标签 前limit条
     *
     * @param limit
     * @return
     */
    public List<Long> findHotsTagIds(Integer limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
