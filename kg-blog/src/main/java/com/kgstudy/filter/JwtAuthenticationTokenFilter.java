package com.kgstudy.filter;

import com.alibaba.fastjson.JSON;
import com.kgstudy.domain.ResponseResult;
import com.kgstudy.domain.entity.LoginUser;
import com.kgstudy.enums.AppHttpCodeEnum;
import com.kgstudy.utils.JwtUtil;
import com.kgstudy.utils.RedisCache;
import com.kgstudy.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/9 16:01
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的 token
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录 直接放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        // 解析获取 userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // 两种情况：token 超时，token 非法
            // 解决方法：响应告诉前端需要重新登录
            ResponseResult errorResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(errorResult));
            return;
        }
        String userId = claims.getSubject();
        // 从 redis 中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
        // 如果 redis 中，获取不到
        if (Objects.isNull(loginUser)) {
            // 说明登录过期，提示重新登录
            ResponseResult errorResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(errorResult));
            return;
        }
        // 存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
