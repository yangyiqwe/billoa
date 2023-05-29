package com.example.billoa.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 出库表
 * </p>
 *
 * @author yanyi
 * @since 2023-03-31
 */
@TableName("outbill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Outbill对象", description = "出库表")
public class Outbill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelProperty("规格/米")
    @ApiModelProperty("规格")
    private double outbillSpci;

    @ExcelProperty("品名")
    @ApiModelProperty("品名")
    private String outbillArtice;

    @ExcelProperty("数量")
    @ApiModelProperty("物品数量")
    private double outbillNum;

    @ExcelProperty("小计/米")
    @ApiModelProperty("小计")
    private double outbillArticleTotl;

    @ExcelProperty("日期")
    @ApiModelProperty("日期")
    private String outbillDate;

    @ExcelProperty("需方")
    @ApiModelProperty("客户")
    private String outbillConnection;

    @ExcelProperty("发货人")
    @ApiModelProperty("发货人")
    private String outbillSendgoodName;

    @ExcelProperty("备注")
    @ApiModelProperty("备注")
    private String outbillContent;

    @ExcelProperty("押金")
    @ApiModelProperty("押金")
    private double outbillCashmoney;

    @ExcelProperty("押金收款人")
    @ApiModelProperty("押金收款人")
    private String outbillCashmoneyman;

    @ExcelProperty("收租金")
    @ApiModelProperty("收租金")
    private double outbillPutrest;

    @ExcelIgnore
    @TableLogic
    private Integer deleted=0;

    @ExcelIgnore
    @TableField(exist = false)
    private double[] outbillSpcis;   //规格数组

    @ExcelIgnore
    @TableField(exist = false)
    private double[] outbillNums;   //数量数组
}
