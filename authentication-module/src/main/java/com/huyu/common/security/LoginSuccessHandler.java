package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.utils.R;
import com.huyu.entity.UserEntity;
import com.huyu.feign.UserFeign;
import com.huyu.utils.JwtUtils;
import com.huyu.utils.RedisUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private UserFeign userFeign;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("-----LoginSuccessHandler");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        //根据用户username生成token
        String username = authentication.getName();
        //此处token过期时间为Long.MAX_VALUE，可以看做永不过期
        String token = JwtUtils.genJwtToken(username);
        //将token和对应用户信息存储到redis中并设置失效时间为半小时
        UserEntity user = JSON.parseObject((String)userFeign.getUserInfoByUsername(username).get("data"), UserEntity.class);
        redisUtils.set(token,JSON.toJSON(user));

        /*UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        //判断如果有权限信息，放到权限上下文中,存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/

        outputStream.write(JSON.toJSONBytes(R.ok("登录成功").put("token",token)));
        outputStream.flush();
        outputStream.close();
    }


}
