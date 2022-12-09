package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/7 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {

    private List rows;
    private Long total;

}
