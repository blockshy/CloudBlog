package com.huyu.common.security;

import com.alibaba.fastjson.JSON;
import com.huyu.common.exception.UserCountLockException;
import com.huyu.entity.UserEntity;
import com.huyu.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库
        UserEntity userEntity = JSON.parseObject((String)userFeign.getUserInfoByUsername(username).get("data"), UserEntity.class);
        System.out.println("loadUserByUsername:"+userEntity);
        //判断
        if(userEntity == null){
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            throw new UsernameNotFoundException("用户名或密码错误！");
        } else if (!userEntity.getEnabled()) {
            throw new UserCountLockException("该用户已被禁用！");
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(),getUserAuthority());
        //如果使用的是hasRole和角色相关的验证方式，例如.antMatchers("/test/index").hasRole("sale")，则在此处写的role需要添加前缀ROLE_
        //List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(null);
    }

    public List<GrantedAuthority> getUserAuthority() {
        return new ArrayList<>();
    }
}
