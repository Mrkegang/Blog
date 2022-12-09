package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/6 13:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {

    private Long id;
    // 标题
    private String title;
    // 访问量
    private Long viewCount;
}
