package com.huyu.config;


import com.huyu.common.constant.WhiteListConstant;
import com.huyu.common.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Resource
    private VerificationCodeFilter verificationCodeFilter;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    //密码MD5加密和密文校验
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        PasswordEncoder passwordEncoder = new PasswordEncoder() {

            //将前端传递回来的密码通过MD5算法加密
            @Override
            public String encode(CharSequence originalPassword) {
                byte[] passwordBytes = originalPassword.toString().getBytes();
                return DigestUtils.md5DigestAsHex(passwordBytes);
            }

            //将加密的密码和数据库中密文进行对比，一致则返回true
            @Override
            public boolean matches(CharSequence ciphertext, String ciphertextDatabase) {
                byte[] ciphertextBytes = ciphertext.toString().getBytes();
                String md5DigestAsHex = DigestUtils.md5DigestAsHex(ciphertextBytes);
                return ciphertextDatabase.equals(md5DigestAsHex);
            }
        };
        //实际上验证用户信息时使用的是userService中的loadUserByUsername方法
        authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    //解决访问静态资源被拦截的问题（开放对静态资源的访问权限）
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加验证码过滤器
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);

        //允许跨域和CSRF拦截
        http.cors().and().csrf().disable()
                //开启表单登录并设置成功或失败的处理方法
                .formLogin()
                //设置登录访问路径（前端访问）
                .loginProcessingUrl("/authorization/login")
                //设置登录成功之后的处理
                .successHandler(loginSuccessHandler)
                //设置登录失败之后的处理
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                //设置登出路径（前端访问）
                .logoutUrl("/authorization/logout")
                //设置登出成功之后的处理方法
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                //关闭session（前后端分离用不到）
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //拦截规则配置
                .and()
                .authorizeRequests()
                .antMatchers(WhiteListConstant.URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
                //异常处理配置
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //自定义过滤器配置
                .and().addFilter(jwtAuthenticationFilter());
    }
}
