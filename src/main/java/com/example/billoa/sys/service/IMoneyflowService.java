package com.example.billoa.sys.service;

import com.example.billoa.common.vo.Result;
import com.example.billoa.sys.entity.Moneyflow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.billoa.sys.entity.Role;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanyi
 * @since 2023-03-20
 */
public interface IMoneyflowService extends IService<Moneyflow> {

    void addMoneyflow(@RequestBody Moneyflow moneyflow);

    void updateMoneyflow(Moneyflow moneyflow);

    void deleteMoneyflowById(Integer id);

    Moneyflow getMoneyflowById(Integer id);

}
