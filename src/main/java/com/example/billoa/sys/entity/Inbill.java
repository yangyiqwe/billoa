package com.example.billoa.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 入库表
 * </p>
 *
 * @author yanyi
 * @since 2023-04-26
 */
@TableName("inbill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Inbill对象", description = "入库表")
public class Inbill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(value = "inbill_id", type = IdType.AUTO)
    private Integer inbillId;

    @ExcelProperty("需方")
    @ApiModelProperty("客户")
    private String inbillConnection;

    @ExcelProperty("日期")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty("日期")
    private Date inbillDate;

    @ExcelProperty("品名")
    @ApiModelProperty("品名")
    private String inbillArtice;

    @ExcelProperty("规格/米")
    @ApiModelProperty("规格/米")
    private double inbillSpci;

    @ExcelProperty("数量")
    @ApiModelProperty("数量")
    private Double inbillNum;

    @ExcelProperty("小计/米")
    @ApiModelProperty("小计/米")
    private Double inbillTotal;

    @ExcelProperty("收货人")
    @ApiModelProperty("收货人")
    private String inbillReceiving;

    @ExcelProperty("备注")
    @ApiModelProperty("备注")
    private String inbillCotent;

    @ExcelProperty("收租金")
    @ApiModelProperty("收租金")
    private Double inbillInrent;

    @ExcelProperty("收款人")
    @ApiModelProperty("收款人")
    private String inbillInrentPeople;

    @ExcelIgnore
    @TableLogic
    private Integer deleted=0;

    @ExcelIgnore
    @TableField(exist = false)
    private double[] inbillSpcis;   //规格数组

    @ExcelIgnore
    @TableField(exist = false)
    private double[] inbillNums;   //数量数组

}
