package com.example.billoa.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.utils.TestFileUtil;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Moneyflow;
import com.example.billoa.sys.entity.Role;
import com.example.billoa.sys.entity.User;
import com.example.billoa.sys.service.IMoneyflowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/moneyflow")
public class MoneyflowController {

    @Autowired
    private IMoneyflowService moneyflowService;

    @PostMapping
    @ApiOperation(value = "新增钢管个人支出账单")
    public Result<?> addMoneyflow(@RequestBody Moneyflow moneyflow){
        moneyflowService.addMoneyflow(moneyflow);
        return Result.success("新增个人账单成功");
    }

    @PutMapping
    @ApiOperation(value = "修改钢管个人支出账单")
    public Result<?> updateMoneyflow(@RequestBody Moneyflow moneyflow){
        moneyflowService.updateMoneyflow(moneyflow);
        return Result.success("修改个人账单成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除钢管个人支出账单")
    public Result<Moneyflow> deleteMoneyflowById(@PathVariable("id") Integer id){
        moneyflowService.deleteMoneyflowById(id);
        return Result.success("删除个人账单成功");
    }

    @GetMapping("/list")
    @ApiOperation(value = "个人支出账单")
    public Result<Map<String,Object>> getMoneyflowList(@RequestParam(value = "pageNo") Long pageNo,
                                                        @RequestParam(value = "pageSize") Long pageSize){

        Page<Moneyflow> page=new Page<>();
        moneyflowService.page(page);

        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个账单")
    public Result<Moneyflow> getMoneyflowById(@PathVariable("id") Integer id){
        Moneyflow moneyflow = moneyflowService.getMoneyflowById(id);
        return Result.success(moneyflow);
    }

    /*@GetMapping("/exportMoneyflow")
    @ApiOperation(value = "导出账单")
    public void exportExcel(HttpServletResponse response){
        List<Moneyflow> moneyflowList = moneyflowService.list();
        String sheetName="个人账单";
        //String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //String fileName ="钢管个人账单";

        //EasyExcel.write(fileName, Moneyflow.class).sheet(sheetName).doWrite(moneyflowList);

        setExcelRespProp(response, "个人账单");

        try {
            EasyExcel.write(response.getOutputStream())
                    .head(Moneyflow.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("会员列表")
                    .doWrite(moneyflowList);
        } catch (IOException e) {
            e.printStackTrace();
        }


        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=个人账单.xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), Moneyflow.class).sheet(sheetName).doWrite(moneyflowList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();
    }*/

    /**
     * 设置excel下载响应头属性
     */
    /*private void setExcelRespProp(HttpServletResponse response, String rawFileName){
        String fileName = null;
        try {
            fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }*/

}
