package com.example.mockblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mockblog.pojo.Comment;
import com.example.mockblog.service.CommentService;
import com.example.mockblog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 15727
* @description 针对表【ms_comment】的数据库操作Service实现
* @createDate 2024-05-28 16:16:52
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




