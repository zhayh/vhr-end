package com.niit.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "职称实体类", description = "职称信息描述")
public class JobLevel {
    @ApiModelProperty(value = "职称id")
    private Integer id;

    @ApiModelProperty(value = "职称名称")
    private String name;

    @ApiModelProperty(value = "职称等级")
    private String titlelevel;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm", timezone = "Asia/Shanghai")
    @ApiModelProperty(value = "职位创建时间")
    private Date createdate;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
}
