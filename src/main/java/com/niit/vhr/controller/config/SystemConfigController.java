package com.niit.vhr.controller.config;

import com.niit.vhr.model.Menu;
import com.niit.vhr.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-17 20:33
 * @description :
 */
@RestController
@RequestMapping("/system/config")
@Api(value = "SystemConfigController", tags = {"系统菜单管理"})
public class SystemConfigController {
    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    @ApiOperation(value = "获取菜单", notes = "根据登录用户id获取菜单")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId();
    }
}
