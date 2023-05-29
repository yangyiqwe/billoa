package com.example.billoa.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.billoa.sys.entity.Menu;
import com.example.billoa.sys.entity.RoleMenu;
import com.example.billoa.sys.mapper.MenuMapper;
import com.example.billoa.sys.mapper.RoleMenuMapper;
import com.example.billoa.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.billoa.sys.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public List<Menu> getAllMenu() {
        // 一级菜单
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0);
        List<Menu> menuList = this.list(wrapper);
        // 填充子菜单
        setMenuChildren(menuList);
        return menuList;
    }

    private void setMenuChildren(List<Menu> menuList) {
        if(menuList != null){
            for (Menu menu : menuList) {
                LambdaQueryWrapper<Menu> subWrapper = new LambdaQueryWrapper<>();
                subWrapper.eq(Menu::getParentId,menu.getMenuId());
                List<Menu> subMenuList = this.list(subWrapper);
                menu.setChildren(subMenuList);
                // 递归
                setMenuChildren(subMenuList);
            }
        }
    }

    @Override
    public List<Menu> getMenuListByUserId(Integer userId) {
        //一级菜单
        List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, 0);
        //二级菜单
        setMenuChildrenByUserId(userId,menuList);
        return menuList;
    }

    @Override
    public void addMenu(Map<String,Object> menu) {
        Menu menuPojo=null;
        Integer parentId=Integer.parseInt((String) menu.get("parentId"));
        if(parentId== 0){//一级菜单
            menu.remove("parent");
            String toJSON = JSONObject.toJSONString(menu);
            menuPojo = JSONObject.toJavaObject(JSON.parseObject(toJSON), Menu.class);
            menuPojo.setParentId(0);
            menuPojo.setIsLeaf("N");
        }
        if(parentId== 1){//二级菜单
            Integer parent= (Integer) menu.get("parent");
            menu.remove("parent");
            String toJSON = JSONObject.toJSONString(menu);
            menuPojo = JSONObject.toJavaObject(JSON.parseObject(toJSON), Menu.class);
            menuPojo.setIsLeaf("Y");
        }
        this.baseMapper.insert(menuPojo);
        LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getName,menu.get("name"));
        Menu menus = this.baseMapper.selectOne(wrapper);
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(1);
        roleMenu.setMenuId(menus.getMenuId());
        roleMenuMapper.insert(roleMenu);
    }

    @Override
    public List<Menu> getMenuByHost() {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0);
        List<Menu> menus = this.baseMapper.selectList(wrapper);
        return menus;
    }

    private void setMenuChildrenByUserId(Integer userId,List<Menu> menuList){
        if(menuList!=null){
            if(menuList!=null){
                for (Menu menu: menuList) {
                    List<Menu> SubMenuList= this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());
                    menu.setChildren(SubMenuList);
                    //递归
                    setMenuChildrenByUserId(userId,SubMenuList);
                }

            }
        }
    }
}
