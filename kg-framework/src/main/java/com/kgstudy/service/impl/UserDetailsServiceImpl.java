package com.kgstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kgstudy.domain.entity.LoginUser;
import com.kgstudy.domain.entity.User;
import com.kgstudy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 15:07
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        // 判断是否查到用户，如果没查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在！");
        }
        // TODO 查询权限信息封装

        // 返回用户信息
        return new LoginUser(user);
    }
}
