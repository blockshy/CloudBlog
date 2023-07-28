package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.common.constant.ReturnCode;
import com.huyu.utils.R;
import com.huyu.utils.RedisUtils;
import com.huyu.utils.StringUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerificationCodeFilter extends GenericFilter {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("-----VerificationCodeFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("req.getMethod():"+req.getMethod());
        System.out.println("req.getServletPath():"+req.getServletPath());
        //只处理登录注册请求的验证码验证
        if("POST".equals(req.getMethod()) && ("/authorization/login".equals(req.getServletPath()) || "/authorization/register".equals(req.getServletPath()))){
            //登录请求
            String code = req.getParameter("verificationCode");
            System.out.println("verificationCode:"+code);
            if(StringUtil.isEmpty(code) || redisUtils.get(code) == null){
                //验证码错误
                res.setContentType("application/json;charset=utf-8");
                PrintWriter out = res.getWriter();
                out.write("/authorization/login".equals(req.getServletPath()) ?
                        JSON.toJSONString(R.error(ReturnCode.LOGIN_ERROR_VERIFICATION_CODE_ERROR,"验证码错误！")) :
                        JSON.toJSONString(R.error(ReturnCode.REGISTER_ERROR_VERIFICATION_CODE_ERROR,"验证码错误！")));
                out.flush();
                out.close();
            }else {
                //校验通过后验证码不再可用，直接删除
                chain.doFilter(req,res);
                redisUtils.del(code);
            }
        }
    }
}
