package com.niit.vhr.model;

import lombok.Data;

import java.util.Date;

@Data
public class JobLevel {
    private Integer id;

    private String name;

    private String titlelevel;

    private Date createdate;

    private Boolean enabled;
}
