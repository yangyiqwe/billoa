package com.example.billoa.sys.service;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.billoa.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-14
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenu();

    List<Menu> getMenuListByUserId(Integer userId);

    void addMenu(Map<String,Object> menu);

    List<Menu> getMenuByHost();
}
