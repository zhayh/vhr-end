package com.niit.vhr.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * menu
 * @author 
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Integer parentId;

    private Boolean enabled;

    private Meta meta;

    private List<Menu> children;

    private List<Role> roles;

}
