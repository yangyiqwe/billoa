package com.example.billoa.sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 品名表
 * </p>
 *
 * @author yanyi
 * @since 2023-03-18
 */

@TableName("article")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Article对象", description = "品名表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty("物品名称")
    private String articleName;

}
