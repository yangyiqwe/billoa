package com.example.billoa.sys.controller;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Menu;
import com.example.billoa.sys.service.IMenuService;
import com.example.billoa.sys.service.IRoleMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping
    @ApiOperation("查询所有菜单数据")
    public Result<List<Menu>> getAllMenu(){
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }

    @PostMapping
    @ApiOperation(value = "新增菜单")
    public Result<?> addMenu(@RequestBody Map<String,Object> menu){
        menuService.addMenu(menu);
        return Result.success("新增菜单成功");
    }

    @GetMapping("/getMenuByHost")
    @ApiOperation(value = "查询一级菜单")
    public Result<List<Menu>> getMenuByHost(){
        List<Menu> menuByHostList = menuService.getMenuByHost();
        return Result.success(menuByHostList);
    }



}
