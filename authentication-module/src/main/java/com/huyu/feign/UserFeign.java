package com.huyu.feign;


import com.huyu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-module")
@RequestMapping("/user")
public interface UserFeign {

    @GetMapping("/user-info/{userId}")
    R getUserInfo(@PathVariable("userId") Integer userId);

    @GetMapping("/user-info/{username}")
    public R getUserInfoByUsername(@PathVariable("username") String username);
}
