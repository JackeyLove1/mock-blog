package com.example.mockblog.service;

import com.example.mockblog.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mockblog.vo.Result;

import java.util.List;

/**
 * @author 15727
 * @description 针对表【ms_tag】的数据库操作Service
 * @createDate 2024-05-28 16:17:11
 */
public interface TagService extends IService<Tag> {
    List<Tag> findTagsByArticleId(Long articleId);

    Result<List<Tag>> hots(int limit);

    /**
     * 查询所有的文章标签
     *
     * @return
     */
    Result<List<Tag>> findAll();

    Result<List<Tag>> findAllDetail();

    Result<Tag> findDetailById(Long id);
}
