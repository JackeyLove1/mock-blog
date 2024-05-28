package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.pojo.Tag;
import com.example.mockblog.service.TagService;
import com.example.mockblog.mapper.TagMapper;
import com.example.mockblog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 15727
* @description 针对表【ms_tag】的数据库操作Service实现
* @createDate 2024-05-28 16:17:11
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> findTagsByArticleId(Long articleId){
        return tagMapper.findTagsByArticleId(articleId);
    }

    @Override
    public Result<List<Tag>> hots(int limit){
        List<Long> tagLists = tagMapper.findHotsTagIds(limit);
        if (tagLists == null){
            return Result.success(null);
        }
        return Result.success(tagMapper.findTagsByTagIds(tagLists));
    }


    /**
     * 查询所有的文章标签
     * @return
     */
    @Override
    public Result<List<Tag>> findAll(){
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        return Result.success(tagMapper.selectList(queryWrapper));
    }

    @Override
    public Result<List<Tag>> findAllDetail(){
        return Result.success(tagMapper.selectList(null));
    }

    @Override
    public Result<Tag> findDetailById(Long id){
        return Result.success(tagMapper.selectById(id));
    }
}




