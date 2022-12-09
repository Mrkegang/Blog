package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/7 13:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Long categoryId;

    private String categoryName;

    private String content;

    private Date createTime;

    private Long id;

    private String isComment;

    private String title;

    private Long viewCount;

}
