package com.huyu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

    private Integer userId;
    private String username;
    private String nickname;
    private String password;
    private Boolean enabled;
}
