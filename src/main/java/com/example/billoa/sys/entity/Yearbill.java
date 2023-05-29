package com.example.billoa.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 年账单
 * </p>
 *
 * @author yanyi
 * @since 2023-04-22
 */
@TableName("yearbill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Yearbill对象", description = "年账单")
public class Yearbill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @TableId(value = "yearbill_id", type = IdType.AUTO)
    private Integer yearbillId;

    @ApiModelProperty("姓名")
    private String yearbillName;

    @ApiModelProperty("租金")
    private String yearbillRent;

    @ApiModelProperty("是否清账")
    private String yearbillSaa;

    @ApiModelProperty("备注")
    private String yearbillContent;

    @ApiModelProperty("出库米数")
    private Double yearbillOutnum;

    @ApiModelProperty("入库米数")
    private Double yearbillInnum;

    @ApiModelProperty("扣子出库")
    private Double yearbillOutkouzi;

    @ApiModelProperty("扣子入库")
    private Double yearbillInkouzi;

    @ApiModelProperty("油顶出库")
    private Double yearbillOutyoudin;

    @ApiModelProperty("油顶入库")
    private Double yearbillInyoudin;

    @ApiModelProperty("侧板出库")
    private Double yearbillOutceban;

    @ApiModelProperty("侧板入库")
    private Double yearbillInceban;

    @ApiModelProperty("模板出库")
    private Double yearbillOutmoban;

    @ApiModelProperty("模板入库")
    private Double yearbillInmoban;

    @ApiModelProperty("套筒出库")
    private Double yearbillOuttaotong;

    @ApiModelProperty("套筒入库")
    private Double yearbillIntaotong;

    @ApiModelProperty("步步紧出库")
    private Double yearbillOutbbjin;

    @ApiModelProperty("步步紧入库")
    private Double yearbillInbbjin;

    @ApiModelProperty("短接出库")
    private Double yearbillOutduanjie;

    @ApiModelProperty("短接入库")
    private Double yearbillInduanjie;

    @ApiModelProperty("年份")
    private String yearbillDate=new SimpleDateFormat("yyyy").format(new Date());

    @Override
    public String toString() {
        return "Yearbill{" +
            "yearbillId=" + yearbillId +
            ", yearbillName=" + yearbillName +
            ", yearbillRent=" + yearbillRent +
            ", yearbillSaa=" + yearbillSaa +
            ", yearbillContent=" + yearbillContent +
            ", yearbillOutnum=" + yearbillOutnum +
            ", yearbillInnum=" + yearbillInnum +
            ", yearbillOutkouzi=" + yearbillOutkouzi +
            ", yearbillInkouzi=" + yearbillInkouzi +
            ", yearbillOutyoudin=" + yearbillOutyoudin +
            ", yearbillInyoudin=" + yearbillInyoudin +
            ", yearbillOutceban=" + yearbillOutceban +
            ", yearbillInceban=" + yearbillInceban +
            ", yearbillOutmoban=" + yearbillOutmoban +
            ", yearbillInmoban=" + yearbillInmoban +
            ", yearbillOuttaotong=" + yearbillOuttaotong +
            ", yearbillIntaotong=" + yearbillIntaotong +
            ", yearbillOutbbjin=" + yearbillOutbbjin +
            ", yearbillInbbjin=" + yearbillInbbjin +
            ", yearbillOutduanjie=" + yearbillOutduanjie +
            ", yearbillInduanjie=" + yearbillInduanjie +
            ", yearbillDate=" + yearbillDate +
        "}";
    }
}
