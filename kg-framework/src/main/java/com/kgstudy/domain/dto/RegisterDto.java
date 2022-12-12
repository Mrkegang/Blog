package com.kgstudy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/12 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String email;

    private String nickName;

    private String password;

    private String userName;
}
