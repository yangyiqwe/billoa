package com.example.billoa.sys.service;

import com.example.billoa.sys.entity.Inbill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.billoa.sys.entity.Outbill;

import java.util.List;

/**
 * <p>
 * 入库表 服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-04-26
 */
public interface IInbillService extends IService<Inbill> {
    void save(List<Inbill> list);
    void addInbill(List<Inbill> list);
    void updateInbill(Inbill inbill);
    Inbill getInbillById(Integer id);
    void deleteInbillById(Integer id);
}
