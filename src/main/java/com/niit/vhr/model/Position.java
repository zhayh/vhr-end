package com.niit.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "职位实体类", description = "职位信息描述")
public class Position {
    @ApiModelProperty(value = "职位id")
    private Integer id;

    @ApiModelProperty(value = "职位名称")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd hh:hh", timezone = "Asia/Shanghai")
    @ApiModelProperty(value = "职位创建时间")
    private Date createDate;

    private Boolean enabled;
}
