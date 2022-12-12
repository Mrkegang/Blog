package com.kgstudy.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    private String UserName;

    private String ToCommentUserName;

    /**
     * 根评论id
     */
    @TableField(value = "root_id")
    private Long rootId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    @TableField(value = "to_comment_user_id")
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    @TableField(value = "to_comment_id")
    private Long toCommentId;

    /**
     * 回复目标评论的用户名
     */
    @TableField(value = "to_comment_id")
    private String toCommentName;

    /**
     *
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 评论创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    private List<CommentVo> children;
}
