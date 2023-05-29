package com.example.billoa.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.utils.DemoDataListener;
import com.example.billoa.common.utils.DemoDataListenerOut;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Connection;
import com.example.billoa.sys.entity.Inbill;
import com.example.billoa.sys.entity.Outbill;
import com.example.billoa.sys.mapper.OutbillMapper;
import com.example.billoa.sys.service.IConnectionService;
import com.example.billoa.sys.service.IOutbillService;
import com.example.billoa.sys.service.impl.InbillServiceImpl;
import com.example.billoa.sys.service.impl.OutbillServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 出库表 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/outbill")
public class OutbillController {

    @Autowired
    private IOutbillService outbillService;

    @Autowired
    private OutbillServiceImpl outbillServiceImpl;

    @GetMapping("/list")
    @ApiOperation(value = "出库列表")
    public Result<Map<String,Object>> list(@RequestParam(value = "outbillConnection",required = false)String outbillConnection,
                                      @RequestParam(value = "startTime",required = false) String startTime,
                                           @RequestParam(value = "endTime",required = false) String endTime,
                                           @RequestParam(value = "outbillArtice",required = false) String outbillArtice,
                                           @RequestParam(value = "outbillSpci",required = false) Double outbillSpci,
                                      @RequestParam(value = "pageNo") Long pageNo,
                                      @RequestParam(value = "pageSize") Long pageSize){


        LambdaQueryWrapper<Outbill> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(outbillConnection),Outbill::getOutbillConnection,outbillConnection);
        wrapper.eq(StringUtils.hasLength(outbillArtice),Outbill::getOutbillArtice,outbillArtice);
        wrapper.eq(outbillSpci != null,Outbill::getOutbillSpci,outbillSpci);
        if(StringUtils.hasLength(startTime))
            wrapper.between(Outbill::getOutbillDate,startTime,endTime);
        wrapper.orderByDesc(Outbill::getOutbillDate);
        Page<Outbill> page=new Page<>(pageNo,pageSize);
        outbillService.page(page,wrapper);

        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    @ApiOperation(value = "新增出库")
    public Result<?> addOutbill(@RequestBody Outbill outbill){
        double[] spcisArr = outbill.getOutbillSpcis();
        double[] numsArr = outbill.getOutbillNums();
        LinkedList<Outbill> list=new LinkedList<>();
        if(spcisArr.length==numsArr.length){
            for (int i = 0; i <spcisArr.length ; i++) {
                Outbill ob=new Outbill();
                ob.setOutbillArtice(outbill.getOutbillArtice());
                ob.setOutbillDate(outbill.getOutbillDate());
                ob.setOutbillConnection(outbill.getOutbillConnection());
                ob.setOutbillSendgoodName(outbill.getOutbillSendgoodName());
                ob.setOutbillContent(outbill.getOutbillContent());
                ob.setOutbillCashmoney(outbill.getOutbillCashmoney());
                ob.setOutbillCashmoneyman(outbill.getOutbillCashmoneyman());
                ob.setOutbillPutrest(outbill.getOutbillPutrest());
                ob.setOutbillSpci(spcisArr[i]);
                ob.setOutbillNum(numsArr[i]);
                double total=spcisArr[i]*numsArr[i];
                ob.setOutbillArticleTotl(total);
                list.add(ob);
                //outbillService.addOutbill(outbill);
            }
            outbillService.addOutbill(list);
            return Result.success("新增出库成功");
        }else
        return Result.success("数据不对");
    }

    @PutMapping
    @ApiOperation(value = "修改出库")
    public Result<?> updateOutbill(@RequestBody Outbill outbill){
        outbillService.updateOutbill(outbill);
        return Result.success("修改出库成功");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取出库id")
    public Result<Outbill> getOutbillById(@PathVariable("id") Integer id){
        Outbill outbill = outbillService.getOutbillById(id);
        return Result.success(outbill);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除出库")
    public Result<Outbill> deleteOutbillById(@PathVariable("id") Integer id){
        outbillService.deleteOutbillById(id);
        return Result.success("删除出库成功");
    }

    @RequestMapping("import")
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            // 读取文件流
            EasyExcel.read
                            (file.getInputStream(),// 前端上传的文件
                                    Outbill.class,// 跟excel对应的实体类
                                    new DemoDataListenerOut(outbillServiceImpl))// 监听器
                    .excelType(ExcelTypeEnum.XLSX)// excel的类型
                    .sheet(0).doRead();
            return Result.success("出库导入成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("出库导入失败");
        }
    }


}
