package com.niit.vhr.model;

import java.io.Serializable;
import lombok.Data;

/**
 * menu_role
 * @author 
 */
@Data
public class MenuRole implements Serializable {
    private Integer id;

    private Integer mid;

    private Integer rid;

    private static final long serialVersionUID = 1L;
}