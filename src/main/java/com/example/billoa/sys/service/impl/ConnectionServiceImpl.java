package com.example.billoa.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.billoa.sys.entity.Article;
import com.example.billoa.sys.entity.Connection;
import com.example.billoa.sys.mapper.ConnectionMapper;
import com.example.billoa.sys.service.IConnectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
@Service
public class ConnectionServiceImpl extends ServiceImpl<ConnectionMapper, Connection> implements IConnectionService {
    @Autowired
    private ConnectionMapper connectionMapper;

    @Override
    @Transactional
    public void addConnection(Connection connection) {
        this.baseMapper.insert(connection);
    }

    @Override
    @Transactional
    public void updateConnection(Connection connection) {
        this.baseMapper.updateById(connection);
    }

    @Override
    public Connection getConnectionById(Integer id) {
        Connection connection = this.baseMapper.selectById(id);
        return connection;
    }

    @Override
    @Transactional
    public void deleteConnectionById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Connection> getConnectionAll() {
        return connectionMapper.selectList(Wrappers.<Connection>lambdaQuery());
    }

}
