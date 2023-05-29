package com.example.billoa.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Connection;
import com.example.billoa.sys.entity.Yearbill;
import com.example.billoa.sys.service.IConnectionService;
import com.example.billoa.sys.service.IYearbillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 年账单 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/yearbill")
public class YearbillController {

    @Autowired
    private IYearbillService yearbillService;

    @GetMapping("/list")
    @ApiOperation(value = "年账单列表")
    public Result<Map<String,Object>> list(@RequestParam(value = "yearbillName",required = false)String yearbillName,
                                           @RequestParam(value = "yearbillDate",required = false)String yearbillDate,
                                           @RequestParam(value = "yearbillSaa",required = false)String yearbillSaa,
                                           @RequestParam(value = "pageNo") Long pageNo,
                                           @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Yearbill> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(yearbillName),Yearbill::getYearbillName,yearbillName);
        wrapper.eq(StringUtils.hasLength(yearbillDate),Yearbill::getYearbillDate,yearbillDate);
        wrapper.eq(StringUtils.hasLength(yearbillSaa),Yearbill::getYearbillSaa,yearbillSaa);
        wrapper.orderByAsc(Yearbill::getYearbillId);

        Page<Yearbill> page=new Page<>(pageNo,pageSize);
        yearbillService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

}
