package com.kgstudy.service;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 柯刚
* @description 针对表【kg_comment(评论表)】的数据库操作Service
* @createDate 2022-12-09 20:21:36
*/
public interface CommentService extends IService<Comment> {


    /**
     * 评论列表
     *
     * @param commentType
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    ResponseResult addComment(Comment comment);

}
