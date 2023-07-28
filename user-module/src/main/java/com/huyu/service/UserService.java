package com.huyu.service;

import com.huyu.utils.R;

public interface UserService {
    R getUserInfo(Integer userId);

    R getUserInfoByUsername(String username);
}
