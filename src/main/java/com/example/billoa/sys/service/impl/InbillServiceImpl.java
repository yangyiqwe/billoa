package com.example.billoa.sys.service.impl;

import com.example.billoa.sys.entity.Inbill;
import com.example.billoa.sys.entity.Outbill;
import com.example.billoa.sys.mapper.InbillMapper;
import com.example.billoa.sys.service.IInbillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 入库表 服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-04-26
 */
@Service
public class InbillServiceImpl extends ServiceImpl<InbillMapper, Inbill> implements IInbillService {

    @Override
    public void save(List<Inbill> list) {
        this.saveBatch(list);
    }

    @Override
    @Transactional
    public void addInbill(List<Inbill> list) {
        this.saveBatch(list);
    }

    @Override
    @Transactional
    public void updateInbill(Inbill inbill) {
        this.baseMapper.updateById(inbill);
    }

    @Override
    public Inbill getInbillById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void deleteInbillById(Integer id) {
        this.baseMapper.deleteById(id);
    }

}
