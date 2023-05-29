package com.example.billoa.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author yanyi
 * @since 2023-03-18
 */
@TableName("connection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Connection对象", description = "客户表")
public class Connection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(value = "connection_id", type = IdType.AUTO)
    private Integer connectionId;


    @ApiModelProperty("客户姓名")
    private String connectionUserName;

    @ApiModelProperty("客户手机")
    private String connectionPhone;

    @ExcelIgnore
    @ApiModelProperty("客户地址")
    private String connectionAddress;

    @ExcelIgnore
    @TableLogic
    private Integer deleted=0;

    @TableField(exist = false)
    private List<Outbill> outbillList;
}
