package com.example.billoa.sys.mapper;

import com.example.billoa.sys.entity.Inbill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 入库表 Mapper 接口
 * </p>
 *
 * @author yanyi
 * @since 2023-04-26
 */
public interface InbillMapper extends BaseMapper<Inbill> {

    //入库表已结账
    List<Map<String,Object>> getCheckOut();

}
