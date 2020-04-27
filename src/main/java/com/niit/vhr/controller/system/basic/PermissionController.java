package com.niit.vhr.controller.system.basic;

import com.niit.vhr.model.Menu;
import com.niit.vhr.model.RespBean;
import com.niit.vhr.model.Role;
import com.niit.vhr.service.MenuService;
import com.niit.vhr.service.RoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-22 15:33
 * @description :
 */

@RestController
@RequestMapping("/system/basic/permiss")
@Api(value = "PermissionController", tags = {"权限组数据管理"})
public class PermissionController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    @ApiOperation(value = "获取所有角色", notes = "所有角色信息列表", produces = "application/json")
    public RespBean getALlRoles() {
        List<Role> roles = roleService.getAllRoles();
        return RespBean.ok("获取成功", roles);
    }

    @GetMapping("/menus")
    @ApiOperation(value = "获取所有三级菜单", notes = "所有三级菜单的列表")
    public RespBean getAllMenus() {
        List<Menu> menus = roleService.getAllMenus();
        return RespBean.ok("获取成功", menus);
    }

    @GetMapping("/mid/{rid}")
    @ApiOperation(value = "获取角色对应的菜单id集合", notes = "角色对应的菜单id列表")
    public RespBean getMidsByRid(@PathVariable Integer rid) {
        List<Integer> rids = menuService.getMidsByRid(rid);
        return RespBean.ok("获取成功", rids);
    }

    @PutMapping("/")
    @ApiOperation(value = "修改角色对应的菜单")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if(menuService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
