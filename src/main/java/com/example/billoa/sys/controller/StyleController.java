package com.example.billoa.sys.controller;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Style;
import com.example.billoa.sys.service.IStyleService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-05-03
 */
@RestController
@RequestMapping("/style")
public class StyleController {
    @Autowired
    private IStyleService styleService;

    @GetMapping("/list")
    @ApiOperation(value = "规格列表")
    public Result<List<Style>> list(){
        return Result.success(styleService.list());
    }

}
