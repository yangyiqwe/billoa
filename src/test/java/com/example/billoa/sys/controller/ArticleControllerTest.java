package com.example.billoa.sys.controller;

import com.example.billoa.sys.entity.Article;
import com.example.billoa.sys.service.IArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleControllerTest {

    @Autowired
    private IArticleService articleService;

    @Test
    void findArticle(){
        List<Article> articleAll = articleService.getArticleAll();
        System.out.println(articleAll);

    }

}
