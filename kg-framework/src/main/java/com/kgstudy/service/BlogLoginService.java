package com.kgstudy.service;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.User;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 14:54
 */
public interface BlogLoginService {

    /**
     * 前端项目用户登录
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 前端项目用户退出登录
     * @return
     */
    ResponseResult logout();
}
