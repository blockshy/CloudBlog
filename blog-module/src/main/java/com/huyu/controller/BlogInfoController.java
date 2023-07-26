package com.huyu.controller;

import com.huyu.entity.BlogInfoEntity;
import com.huyu.service.BlogInfoService;
import com.huyu.utils.R;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blog")
public class BlogInfoController {

    @Resource
    private BlogInfoService blogInfoService;

    /**
     * 获取博客详情
     * @param blogId 博客ID
     * @return 博客详情信息
     */
    @GetMapping("/blog-info/{blogId}")
    public R getBlogInfo(@PathVariable("blogId") Integer blogId){
        System.out.println("blogId:"+blogId);
        return blogInfoService.getBlogInfo(blogId);
    }

    /**
     * 新建博客
     * @param blogInfoEntity 博客信息
     * @return 新建结果
     */
    @PostMapping("/blog-info")
    public R addBlogInfo(@RequestBody BlogInfoEntity blogInfoEntity){
        System.out.println(blogInfoEntity);
        return null;
    }

    /**
     * 修改博客
     * @param blogInfoEntity 博客信息-新
     * @return 修改结果
     */
    @PutMapping("/blog-info")
    public R editBlogInfo(@RequestBody BlogInfoEntity blogInfoEntity){
        System.out.println(blogInfoEntity);
        return null;
    }

    /**
     * 删除博客
     * @param blogId 博客ID
     * @return 删除结果
     */
    @PutMapping("/blog-info/{blogId}")
    public R deleteBlogInfo(@PathVariable("blogId") Integer blogId){
        System.out.println(blogId);
        return null;
    }
}
