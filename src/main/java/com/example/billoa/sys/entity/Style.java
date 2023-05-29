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
 *  钢管规格表
 * </p>
 *
 * @author yanyi
 * @since 2023-05-03
 */
@TableName("style")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Style对象", description = "钢管规格表")
public class Style implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer styleId;

    private Double styleName;

}
