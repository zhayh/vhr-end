package com.niit.vhr.model;

import java.io.Serializable;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String name;

    /**
     * 角色名称
     */
    private String nameZh;

    private static final long serialVersionUID = 1L;
}
