package com.example.billoa.sys.service;

import com.example.billoa.sys.entity.Connection;
import com.example.billoa.sys.entity.Outbill;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 出库表 服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
public interface IOutbillService extends IService<Outbill> {
    void save(List<Outbill> list);
    void addOutbill(List<Outbill> list);
    void updateOutbill(Outbill outbill);
    Outbill getOutbillById(Integer id);
    void deleteOutbillById(Integer id);

}
