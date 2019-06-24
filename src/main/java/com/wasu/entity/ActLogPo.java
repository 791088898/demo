package com.wasu.entity;

/**
 * @Author: cs
 * @Date: 2019/6/13 22:07
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "埋点的实体对象")
public class ActLogPo implements Serializable {

    @ApiModelProperty(value = "主键ID",required = true)
    private Integer id;
    @ApiModelProperty(value = "机顶盒号",required = true)
    private String stbid;
    @ApiModelProperty(value = "活动标示",required = true)
    private String piid;
}
