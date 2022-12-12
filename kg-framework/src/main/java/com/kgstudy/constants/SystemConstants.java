package com.kgstudy.constants;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/6 13:41
 */
public class SystemConstants {

    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    public static final String  STATUS_NORMAL = "0";

    /**
     * 友链审核正常通过
     */
    public static final String  LINK_STATUS_NORMAL = "0";

    /**
     * 根评论 id 用 -1 标识
     */
    public static final int ROOT_COMMENT_ID = -1;

    /**
     * 评论类型：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";

    /**
     * 评论类型：友链评论
     */
    public static final String LINK_COMMENT = "1";
}
