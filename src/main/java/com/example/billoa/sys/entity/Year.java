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
 *  年份表
 * </p>
 *
 * @author yanyi
 * @since 2023-05-03
 */
@TableName("year")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Year对象", description = "年份")
public class Year implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer yearId;

    @ApiModelProperty("年份")
    private String yearName;

    @Override
    public String toString() {
        return "Year{" +
            "yearId=" + yearId +
            ", yearName=" + yearName +
        "}";
    }
}
