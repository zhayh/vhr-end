package com.niit.vhr.model;

import lombok.Data;

import java.util.Date;

@Data
public class Position {
    private Integer id;

    private String name;

    private Date createDate;

    private Boolean enabled;

}
