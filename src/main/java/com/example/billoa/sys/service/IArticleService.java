package com.example.billoa.sys.service;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.billoa.sys.entity.User;

import java.util.List;

/**
 * <p>
 * 物品表 服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-18
 */
public interface IArticleService extends IService<Article> {

    List<Article> getArticleAll();
}
