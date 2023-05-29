package com.example.billoa.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Connection;
import com.example.billoa.sys.entity.User;
import com.example.billoa.sys.mapper.ConnectionMapper;
import com.example.billoa.sys.service.IConnectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private ConnectionMapper connectionMapper;

    @Autowired
    private IConnectionService connectionService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    public Result<Map<String,Object>> list(@RequestParam(value = "connectionUserName",required = false)String connectionUserName,
                                         @RequestParam(value = "pageNo") Long pageNo,
                                         @RequestParam(value = "pageSize") Long pageSize){
        /*List<Connection> data = connectionMapper.selectList(Wrappers.<Connection>lambdaQuery()
                .eq(StringUtils.hasLength(connectionUserName),Connection::getConnectionUserName, connectionUserName)
                .orderByAsc(Connection::getConnectionId));*/

        LambdaQueryWrapper<Connection> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(connectionUserName),Connection::getConnectionUserName,connectionUserName);
        wrapper.orderByAsc(Connection::getConnectionId);

        Page<Connection> page=new Page<>(pageNo,pageSize);
        connectionService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    @ApiOperation(value = "新增客户")
    public Result<?> addConnection(@RequestBody Connection connection){
        connectionService.addConnection(connection);
        return Result.success("新增客户成功");
    }

    @PutMapping
    @ApiOperation(value = "修改客户")
    public Result<?> updateConnection(@RequestBody Connection connection){
        connectionService.updateConnection(connection);
        return Result.success("修改客户成功");
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取客户id")
    public Result<Connection> getConnectionById(@PathVariable("id") Integer id){
        Connection connection = connectionService.getConnectionById(id);
        return Result.success(connection);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除客户")
    public Result<Connection> deleteConnectionById(@PathVariable("id") Integer id){
        connectionService.deleteConnectionById(id);
        return Result.success("删除客户成功");
    }

    @GetMapping("/getConnectionAll")
    @ApiOperation(value = "查询所有客户")
    public Result<List<Connection>> getConnectionAll(){
        List<Connection> data = connectionService.getConnectionAll();
        return  Result.success(data);
    }



}
