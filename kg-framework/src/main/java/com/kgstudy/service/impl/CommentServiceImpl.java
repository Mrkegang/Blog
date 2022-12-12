package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgstudy.constants.SystemConstants;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.Comment;
import com.kgstudy.domain.vo.CommentVo;
import com.kgstudy.domain.vo.PageVo;
import com.kgstudy.enums.AppHttpCodeEnum;
import com.kgstudy.exception.SystemException;
import com.kgstudy.mapper.CommentMapper;
import com.kgstudy.service.CommentService;
import com.kgstudy.service.UserService;
import com.kgstudy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 柯刚
* @description 针对表【kg_comment(评论表)】的数据库操作Service实现
* @createDate 2022-12-09 20:21:36
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 需要查询文章评论时再加上评论id，对 articleId 进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        // 根评论 rootId 为 -1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT_ID);
        // 评论类型
        queryWrapper.eq(Comment::getType, commentType);

        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page,queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());

        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        // TODO 改写成 Stream 流的写法
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        // 遍历 vo 集合
        // TODO 改写成 Stream 流的写法
        for (CommentVo commentVo : commentVos) {
            // 通过 creatyBy 查询根用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUserName(nickName);
            // 通过 toCommentUserId 查询子用户的昵称并赋值
            // 如果 toCommentUserId 不为 -1 才进行查询
            if (commentVo.getToCommentId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }

    /**
     * 根据根评论的 id 查询所对应的子评论的集合
     * @param RootId
     * @return
     */
    private List<CommentVo> getChildren(Long RootId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, RootId);
        queryWrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

}




