package com.huyu.common.constant;

public class ReturnCode {

    //登录时验证码错误
    public static final Integer LOGIN_ERROR_VERIFICATION_CODE_ERROR = 100;
    //登录时账用户名或密码错误
    public static final Integer LOGIN_ERROR_INFO_ERROR = 101;
    //注册时验证码错误
    public static final Integer REGISTER_ERROR_VERIFICATION_CODE_ERROR = 102;
    //注册时用户名已存在或不合法
    public static final Integer REGISTER_ERROR_USERNAME_EXISTS = 103;
    //注册时昵称已存在或不合法
    public static final Integer REGISTER_ERROR_NICKNAME_EXISTS = 104;
    //注册时两次密码不一致
    public static final Integer REGISTER_ERROR_TWO_PASSWORD_UNEQUALS = 105;
    //注册时邮箱格式错误
    public static final Integer REGISTER_ERROR_EMAIL_FORMAT_ERROR = 106;
    //注册时邮箱重复
    public static final Integer REGISTER_ERROR_EMAIL_EXISTS = 107;
    //注册时密码不合法
    public static final Integer REGISTER_ERROR_PASSWORD_ERROR = 108;
    //登录时用户名不合法
    public static final Integer LOGIN_ERROR_USERNAME_ERROR = 109;
    //登录时密码不合法
    public static final Integer LOGIN_ERROR_PASSWORD_ERROR = 110;
    //注册时插入数据库出错
    public static final Integer REGISTER_ERROR_INSERT_DATABASE_ERROR = 111;
    //点赞收藏操作时还未登陆
    public static final Integer LIKES_AND_FAVORITES_ERROR_NOT_LOGIN_ERROR = 112;
    //点赞收藏操作时类型错误
    public static final Integer LIKES_AND_FAVORITES_ERROR_TYPE_ERROR = 113;
    //点赞收藏操作时操作失败
    public static final Integer LIKES_AND_FAVORITES_UPDATE_ERROR = 114;
    //获取用户信息时出错
    public static final Integer USER_DETAILS_ERROR = 115;
    //修改用户信息时未登录
    public static final Integer UPDATE_USER_INFO_NO_LOGIN_ERROR = 116;
    //修改用户信息时数据校验出错
    public static final Integer UPDATE_USER_INFO_DATA_FORMAT_ERROR = 117;
    //修改用户信息时修改项错误
    public static final Integer UPDATE_USER_INFO_TYPE_ERROR = 118;
    //修改用户信息时数据库操作出错
    public static final Integer UPDATE_USER_INFO_DATABASE_ERROR = 119;
    //上传用户头像时格式错误
    public static final Integer UPLOAD_USER_AVATAR_FORMAT_ERROR = 120;
    //上传用户头像时sftp传输失败
    public static final Integer UPLOAD_USER_AVATAR_SFTP_ERROR = 121;
    //上传用户头像时大小错误
    public static final Integer UPLOAD_USER_AVATAR_SIZE_ERROR = 122;
    //上传用户头像时文件错误（通常为空）
    public static final Integer UPLOAD_USER_AVATAR_FILE_ERROR = 123;
    //修改密码时出错
    public static final Integer UPDATE_USER_PASSWORD_ERROR = 124;
    //上传壁纸时类型出错
    public static final Integer UPLOAD_WALLPAPER_CATEGORY_ERROR = 125;
    //上传壁纸时壁纸格式出错
    public static final Integer UPLOAD_WALLPAPER_FORMAT_ERROR = 126;
    //上传壁纸时文件出错
    public static final Integer UPLOAD_WALLPAPER_FILE_ERROR = 127;
    //上传壁纸时文件保存出错
    public static final Integer UPLOAD_WALLPAPER_SFTP_ERROR = 128;
    //上传壁纸时保存数据库出错
    public static final Integer UPLOAD_WALLPAPER_DATABASE_ERROR = 129;
    //上传壁纸时tag出错
    public static final Integer UPLOAD_WALLPAPER_TAG_ERROR = 130;
    //修改壁纸信息时类型错误
    public static final Integer EDIT_WALLPAPER_CATEGORY_ERROR = 131;
    //修改壁纸信息时tag错误
    public static final Integer EDIT_WALLPAPER_TAG_ERROR = 132;
    //修改壁纸信息时数据库错误
    public static final Integer EDIT_WALLPAPER_DATABASE_ERROR = 133;
    public static final Integer UPLOAD_WALLPAPER_EXISTS_ERROR = 134;

}
