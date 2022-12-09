package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/7 10:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {

    private Long id;

    private String title;

    private String summary;

    private String categoryName;

    private Long viewCount;

    private Date createTime;

    private String thumbnail;

    private Long likeCount;
}
