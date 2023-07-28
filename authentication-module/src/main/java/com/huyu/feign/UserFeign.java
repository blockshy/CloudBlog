package com.huyu.feign;


import com.huyu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-module")
public interface UserFeign {

    @GetMapping("/user/user-info/{userId}")
    R getUserInfo(@PathVariable("userId") Integer userId);

    @GetMapping("/user/user-info-username/{username}")
    R getUserInfoByUsername(@PathVariable("username") String username);
}
