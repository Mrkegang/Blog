package com.kgstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.dto.UserInfoDto;
import com.kgstudy.domain.entity.User;

/**
* @author 柯刚
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-12-09 22:26:08
*/
public interface UserService extends IService<User> {

    /**
     * 查询用户信息
     * @return
     */
    ResponseResult userInfo();


    /**
     * 更新用户信息
     * @param userInfoDto
     * @return
     */
    ResponseResult updateUserInfo(UserInfoDto userInfoDto);

    /**
     * 用户注册
     * @param registerDto
     * @return
     */
    ResponseResult register(User user);
}
