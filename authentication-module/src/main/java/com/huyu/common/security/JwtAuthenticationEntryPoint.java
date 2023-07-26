package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt认证失败处理
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("-----JwtAuthenticationEntryPoint"+e.getMessage());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        outputStream.write(JSON.toJSONBytes(R.error(HttpServletResponse.SC_UNAUTHORIZED,"认证失败，请登录")));
        outputStream.flush();
        outputStream.close();
    }
}
