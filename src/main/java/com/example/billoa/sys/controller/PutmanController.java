package com.example.billoa.sys.controller;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Putman;
import com.example.billoa.sys.service.IPutmanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 收货收款人员表 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/putman")
public class PutmanController {

    @Autowired
    private IPutmanService putmanService;

    @GetMapping("/list")
    @ApiOperation(value = "收货收款人员列表")
    public Result<List<Putman>> list(){
        return Result.success(putmanService.list());
    }

}
