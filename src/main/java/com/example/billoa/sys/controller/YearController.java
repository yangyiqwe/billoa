package com.example.billoa.sys.controller;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Year;
import com.example.billoa.sys.service.IYearService;
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
@RequestMapping("/year")
public class YearController {
    @Autowired
    private IYearService yearService;

    @GetMapping("/list")
    @ApiOperation(value = "年份列表")
   public Result<List<Year>>  list(){
       List<Year> data = yearService.list();
       return  Result.success(data);
   }

}
