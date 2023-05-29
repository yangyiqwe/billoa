package com.example.billoa.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 *  账单流水表
 * </p>
 *
 * @author yanyi
 * @since 2023-03-20
 */
@TableName("moneyflow")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "Moneyflow对象", description = "账单流水表")
public class Moneyflow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(value = "moneyflow_id", type = IdType.AUTO)
    private Integer moneyflowId;

    @DateTimeFormat("yyyy年MM月dd日")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @ExcelProperty("时间")
    @ColumnWidth(20)
    @ApiModelProperty("时间")
    private Date moneyflowDate;

    @ExcelProperty("地点")
    @ApiModelProperty("地点")
    private String moneyflowPlace;

    @ExcelProperty("流水名字")
    @ApiModelProperty("流水名字")
    private String moneyflowName;

    @ExcelProperty("内容")
    @ApiModelProperty("内容")
    private String moneyflowInfo;

    @ExcelProperty("金额")
    @ApiModelProperty("金额")
    private String moneyflowMoney;

    @ExcelIgnore
    @TableLogic
    private Integer deleted=0;


}
