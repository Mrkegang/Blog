package com.kgstudy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/12 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    private String avatar;

    private String email;

    private Long id;

    private String nickName;

    private String sex;
}
