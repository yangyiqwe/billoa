package com.example.billoa.sys.service.impl;

import com.example.billoa.sys.entity.Outbill;
import com.example.billoa.sys.mapper.OutbillMapper;
import com.example.billoa.sys.service.IOutbillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 出库表 服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
@Service
public class OutbillServiceImpl extends ServiceImpl<OutbillMapper, Outbill> implements IOutbillService {

    public void save(List<Outbill> list) {
        this.saveBatch(list);
    }

    @Override
    @Transactional
    public void addOutbill(List<Outbill> list) {
        //this.baseMapper.insert(outbill);
        this.saveBatch(list);
    }

    @Override
    @Transactional
    public void updateOutbill(Outbill outbill) {
        this.baseMapper.updateById(outbill);
    }

    @Override
    public Outbill getOutbillById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void deleteOutbillById(Integer id) {
        this.baseMapper.deleteById(id);
    }

}
