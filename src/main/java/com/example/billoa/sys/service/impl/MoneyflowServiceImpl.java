package com.example.billoa.sys.service.impl;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Moneyflow;
import com.example.billoa.sys.mapper.MoneyflowMapper;
import com.example.billoa.sys.service.IMoneyflowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-20
 */
@Service
public class MoneyflowServiceImpl extends ServiceImpl<MoneyflowMapper, Moneyflow> implements IMoneyflowService {

    @Override
    @Transactional
    public void addMoneyflow(Moneyflow moneyflow) {
       this.baseMapper.insert(moneyflow);
    }

    @Override
    public void updateMoneyflow(Moneyflow moneyflow) {
        this.baseMapper.updateById(moneyflow);
    }

    @Override
    public void deleteMoneyflowById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public Moneyflow getMoneyflowById(Integer id) {
        Moneyflow moneyflow = this.baseMapper.selectById(id);
        return moneyflow;
    }
}
