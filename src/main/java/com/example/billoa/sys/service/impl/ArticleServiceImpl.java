package com.example.billoa.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Article;
import com.example.billoa.sys.mapper.ArticleMapper;
import com.example.billoa.sys.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 物品表 服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getArticleAll() {
        return articleMapper.selectList(Wrappers.<Article>lambdaQuery());
    }
}
