package com.niit.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Position {
    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd hh:hh", timezone = "Asia/Shanghai")
    private Date createDate;

    private Boolean enabled;
}
