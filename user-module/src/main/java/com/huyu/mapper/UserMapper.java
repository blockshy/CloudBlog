package com.huyu.mapper;

import com.huyu.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_id = #{userId}")
    UserEntity getUserInfo(Integer userId);

    @Select("select * from user where username = #{username}")
    UserEntity getUserInfoByUsername(@Param("username") String username);
}
