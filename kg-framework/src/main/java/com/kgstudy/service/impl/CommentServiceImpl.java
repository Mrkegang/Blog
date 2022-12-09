package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgstudy.domain.entity.Comment;
import com.kgstudy.service.CommentService;
import com.kgstudy.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 柯刚
* @description 针对表【kg_comment(评论表)】的数据库操作Service实现
* @createDate 2022-12-09 20:21:36
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




