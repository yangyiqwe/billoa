package com.example.billoa.sys.service;

import com.example.billoa.sys.entity.Connection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.billoa.sys.entity.User;

import java.util.List;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
public interface IConnectionService extends IService<Connection> {

    void addConnection(Connection connection);

    void updateConnection(Connection connection);

    Connection getConnectionById(Integer id);

    void deleteConnectionById(Integer id);

    List<Connection> getConnectionAll();

}
