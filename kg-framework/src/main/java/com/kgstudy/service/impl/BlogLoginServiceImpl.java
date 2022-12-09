package com.kgstudy.service.impl;

import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.LoginUser;
import com.kgstudy.domain.entity.User;
import com.kgstudy.domain.vo.BlogUserLoginVo;
import com.kgstudy.domain.vo.UserInfoVo;
import com.kgstudy.enums.AppHttpCodeEnum;
import com.kgstudy.service.BlogLoginService;
import com.kgstudy.utils.BeanCopyUtils;
import com.kgstudy.utils.JwtUtil;
import com.kgstudy.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 14:56
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误!");
        }
        // 获取 userId 生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把用户信息存入 redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);

        // 把 token 和 userId 封装，返回
        // 把 User 转换成 UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo userLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(userLoginVo);
    }

    @Override
    public ResponseResult logout() {
        // 获取 token，解析获取 userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取 userId
        Long userId = loginUser.getUser().getId();
        // 删除 redis 中的用户信息
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
