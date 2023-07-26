package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.utils.R;
import com.huyu.utils.RedisUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义logout处理
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
        System.out.println("-----JwtLogoutSuccessHandler");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        //清除redis中token
        redisUtils.del(httpServletRequest.getHeader("token"));
        outputStream.write(JSON.toJSONBytes(R.ok("登出成功")));
        outputStream.flush();
        outputStream.close();
    }
}
