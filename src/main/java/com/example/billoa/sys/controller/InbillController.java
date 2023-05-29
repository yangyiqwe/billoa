package com.example.billoa.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.utils.DemoDataListener;
import com.example.billoa.common.utils.TestFileUtil;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Inbill;
import com.example.billoa.sys.entity.Outbill;
import com.example.billoa.sys.mapper.InbillMapper;
import com.example.billoa.sys.service.IInbillService;
import com.example.billoa.sys.service.IOutbillService;
import com.example.billoa.sys.service.impl.InbillServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 入库表 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-04-26
 */
@RestController
@RequestMapping("/inbill")
public class InbillController {

    @Autowired
    private IInbillService inbillService;
    @Autowired
    private InbillServiceImpl inbillServiceimpl;
    @Autowired
    private InbillMapper inbillMapper;

    @GetMapping("/list")
    @ApiOperation(value = "入库列表")
    public Result<Map<String,Object>> list(@RequestParam(value = "inbillConnection",required = false)String inbillConnection,
                                           @RequestParam(value = "startTime",required = false) String startTime,
                                           @RequestParam(value = "endTime",required = false) String endTime,
                                           @RequestParam(value = "inbillArtice",required = false) String inbillArtice,
                                           @RequestParam(value = "inbillSpci",required = false) Double inbillSpci,
                                           @RequestParam(value = "pageNo") Long pageNo,
                                           @RequestParam(value = "pageSize") Long pageSize){


        LambdaQueryWrapper<Inbill> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(inbillConnection),Inbill::getInbillConnection,inbillConnection);
        wrapper.eq(StringUtils.hasLength(inbillArtice),Inbill::getInbillArtice,inbillArtice);
        wrapper.eq(inbillSpci != null,Inbill::getInbillSpci,inbillSpci);
        if(StringUtils.hasLength(startTime))
            wrapper.between(Inbill::getInbillDate,startTime,endTime);
        wrapper.orderByDesc(Inbill::getInbillDate);
        Page<Inbill> page=new Page<>(pageNo,pageSize);
        inbillService.page(page,wrapper);

        Map<String,Object> data=new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    @ApiOperation(value = "新增入库")
    public Result<?> addInbill(@RequestBody Inbill inbill){
        double[] spcisArr = inbill.getInbillSpcis();
        double[] numsArr = inbill.getInbillNums();
        LinkedList<Inbill> list=new LinkedList<>();
        if(spcisArr.length==numsArr.length){
            for (int i = 0; i <spcisArr.length ; i++) {
                Inbill ob=new Inbill();
                ob.setInbillArtice(inbill.getInbillArtice());
                ob.setInbillDate(inbill.getInbillDate());
                ob.setInbillConnection(inbill.getInbillConnection());
                ob.setInbillReceiving(inbill.getInbillReceiving());
                ob.setInbillCotent(inbill.getInbillCotent());
                ob.setInbillInrent(inbill.getInbillInrent());
                ob.setInbillSpci(spcisArr[i]);
                ob.setInbillNum(numsArr[i]);
                double total=spcisArr[i]*numsArr[i];
                ob.setInbillTotal(total);
                list.add(ob);
            }
            inbillService.addInbill(list);
            return Result.success("新增出库成功");
        }else
            return Result.success("数据不对");
    }

    @PutMapping
    @ApiOperation(value = "修改入库")
    public Result<?> updateInbill(@RequestBody Inbill inbill){
        inbillService.updateInbill(inbill);
        return Result.success("修改入库成功");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取出库id")
    public Result<Inbill> getOutbillById(@PathVariable("id") Integer id){
        return Result.success(inbillService.getInbillById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除入库")
    public Result<Inbill> deletInbillById(@PathVariable("id") Integer id){
        inbillService.deleteInbillById(id);
        return Result.success("删除出库成功");
    }

    /**
     * 入库表导入
     * @param file
     * @return
     */
    @RequestMapping("import")
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            // 读取文件流
            EasyExcel.read
                            (file.getInputStream(),// 前端上传的文件
                                    Inbill.class,// 跟excel对应的实体类
                                    new DemoDataListener(inbillServiceimpl))// 监听器
                    .excelType(ExcelTypeEnum.XLSX)// excel的类型
                    .sheet(0).doRead();
            return Result.success("入库导入成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("入库导入失败");
        }

        /*String url="C:\\Users\\86155\\Desktop\\数据库出库.xlsx";
        File file=new File(url);
        try (ExcelReader excelReader = EasyExcel.read(file, Inbill.class, new DemoDataListener(inbillServiceimpl)).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
            excelReader.finish();
            return Result.success("入库导入成功！");
        }catch (Exception e){
            return Result.fail();
        }*/
    }

    /**
     * 已结账导出
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkoutexport")
    @ResponseBody
    public Result<?> load(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> checkOut = inbillMapper.getCheckOut();
        List<List<Object>> dataList=new ArrayList<>();
        for (Map<String, Object> map : checkOut) {
            List<Object> data=new ArrayList<>();
            String date=new SimpleDateFormat("MM月dd日").format(map.get("inbill_date"));
            data.add(date);
            data.add(map.get("inbill_connection"));
            data.add(map.get("inbill_inrent"));
            data.add(map.get("year"));
            data.add(map.get("inbill_cotent"));
            dataList.add(data);
        }
        // 写法1
        String fileName ="已结账" + System.currentTimeMillis()+"";
        ServletOutputStream servletOutputStream = servletOutputStream(fileName, response);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(servletOutputStream).head(head()).sheet("模板").doWrite(dataList);
        servletOutputStream.flush();
        return null;
    }

    public static ServletOutputStream servletOutputStream(String filename, HttpServletResponse response) throws Exception {
        try {
            filename = URLEncoder.encode(filename, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    private List<List<String>> head() {
        List<List<String>> list = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("日期");
        List<String> head1 = ListUtils.newArrayList();
        head1.add("名字");
        List<String> head2 = ListUtils.newArrayList();
        head2.add("租金");
        List<String> head3 = ListUtils.newArrayList();
        head3.add("年份");
        List<String> head4 = ListUtils.newArrayList();
        head4.add("备注");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        return list;
    }


}
