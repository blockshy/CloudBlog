package com.huyu.mapper;

import com.huyu.entity.BlogInfoEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BlogInfoMapper {


    @Select("select * from blog_info where info_id = #{blogId}")
    @Results(id = "blogInfoEntity",value = {
        @Result(id = true,column = "infoId",property = "info_id"),
        @Result(column = "infoName",property = "info_name"),
        @Result(column = "infoContent",property = "info_content"),
        @Result(column = "infoAuthorId",property = "info_author_id"),
        @Result(column = "infoReleaseTime",property = "info_release_time"),
        @Result(column = "infoEditTime",property = "info_edit_time"),
    })
    BlogInfoEntity getBlogInfo(@Param("blogId") Integer blogId);
}
