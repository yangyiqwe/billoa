package com.example.billoa.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Article;
import com.example.billoa.sys.entity.User;
import com.example.billoa.sys.service.IArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 物品表 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-03-18
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @GetMapping("/list")
    @ApiOperation(value = "品名列表")
    public Result<List<Article>> getArticleAll(){
        List<Article> data = articleService.getArticleAll();
        return Result.success(data);
    }

}
