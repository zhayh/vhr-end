package com.niit.vhr.model;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : zhayh
 * @date : 2020-4-17 20:21
 * @description :
 */
@Data
public class Meta {
    private Boolean keepAlive;

    private Boolean requireAuth;

    private static final long serialVersionUID = 1L;
}
