package com.kgstudy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 15:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {

    private String token;

    private UserInfoVo userInfoVo;

}
