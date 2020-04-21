package com.niit.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhayh
 * @date : 2020-4-14 22:36
 * @description :
 */

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String basic() {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advance/hello")
    public String advance() {
        return "/employee/advance/hello";
    }
}
