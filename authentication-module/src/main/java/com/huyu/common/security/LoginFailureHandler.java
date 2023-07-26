package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.utils.R;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        System.out.println("-----LoginFailureHandler");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String message = e.getMessage();
        if(e instanceof BadCredentialsException){
            message = "用户名或密码错误";
        }
        outputStream.write(JSON.toJSONBytes(R.error(message)));
        outputStream.flush();
        outputStream.close();
    }
}
