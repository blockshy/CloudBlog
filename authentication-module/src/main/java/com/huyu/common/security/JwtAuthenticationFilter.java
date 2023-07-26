package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huyu.common.constant.WhiteListConstant;
import com.huyu.entity.UserEntity;
import com.huyu.feign.UserFeign;
import com.huyu.utils.RedisUtils;
import com.huyu.utils.StringUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private RedisUtils redisUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("-----JwtAuthenticationFilter");
        //从请求头中获取token信息
        String token = request.getHeader("token");
        System.out.println("请求url："+request.getRequestURI());
        //只处理白名单中的请求，其他请求全都拦截拒绝
        if(!new ArrayList<>(Arrays.asList(WhiteListConstant.URL_WHITELIST)).contains(request.getRequestURI())){
            return;
        }
        if(!StringUtil.isEmpty(token) && redisUtils.get(token) != null){
            //token存在redis中，证明为有效token，即已登录，已登录则直接重定向到首页
            response.sendRedirect("/");
        }else{
            //token不存在，则为未登录，执行后续登录操作
            chain.doFilter(request,response);
        }
    }
}