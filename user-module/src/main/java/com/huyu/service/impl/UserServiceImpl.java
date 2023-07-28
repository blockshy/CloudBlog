package com.huyu.service.impl;

import com.huyu.entity.UserEntity;
import com.huyu.mapper.UserMapper;
import com.huyu.service.UserService;
import com.huyu.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public R getUserInfo(Integer userId) {
        UserEntity userEntity = userMapper.getUserInfo(userId);
        if(userEntity != null && userEntity.getUserId()!=null){
            return R.ok().put("data",userEntity);
        }else {
            return R.ok("用户不存在！");
        }
    }

    @Override
    public R getUserInfoByUsername(String username) {
        UserEntity userEntity = userMapper.getUserInfoByUsername(username);
        if(userEntity != null && userEntity.getUserId()!=null){
            return R.ok().put("data",userEntity);
        }else {
            return R.ok("用户不存在！");
        }

    }
}
