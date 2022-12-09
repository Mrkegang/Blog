package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/7 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllLinkVo {

    private String address;

    private String description;

    private Long id;

    private String logo;

    private String name;
}
