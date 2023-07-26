package com.huyu.service.impl;

import com.huyu.entity.BlogInfoEntity;
import com.huyu.mapper.BlogInfoMapper;
import com.huyu.service.BlogInfoService;
import com.huyu.utils.R;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Resource
    private BlogInfoMapper blogInfoMapper;
    @Override
    public R getBlogInfo(Integer blogId) {
        BlogInfoEntity blogInfoEntity = blogInfoMapper.getBlogInfo(blogId);
        System.out.println(blogInfoEntity);
        if(blogInfoEntity != null && blogInfoEntity.getInfoId() != null){
            return R.ok().put("data",blogInfoEntity);
        }else {
            return R.error("未查询到该博客!");
        }
    }
}
