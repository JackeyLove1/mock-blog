package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.pojo.Category;
import com.example.mockblog.service.CategoryService;
import com.example.mockblog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 15727
* @description 针对表【ms_category】的数据库操作Service实现
* @createDate 2024-05-28 16:16:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




