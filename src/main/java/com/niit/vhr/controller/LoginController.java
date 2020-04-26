package com.niit.vhr.controller;

import com.niit.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : zhayh
 * @date : 2020-4-15 07:05
 * @description :
 */

@RestController
@ApiIgnore
public class LoginController {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录");
    }
}
