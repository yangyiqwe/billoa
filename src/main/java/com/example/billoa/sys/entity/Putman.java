package com.example.billoa.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 收货收款人员表
 * </p>
 *
 * @author yanyi
 * @since 2023-05-04
 */
@TableName("putman")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Putman对象", description = "收货收款人员表")
public class Putman implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer putmanId;

    @ApiModelProperty("收货收款人")
    private String putmanName;

}
