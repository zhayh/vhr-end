package com.niit.vhr.model;

import java.io.Serializable;
import lombok.Data;

/**
 * hr_role
 * @author 
 */
@Data
public class HrRole implements Serializable {
    private Integer id;

    private Integer hrid;

    private Integer rid;
}
