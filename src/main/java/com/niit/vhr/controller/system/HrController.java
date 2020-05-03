package com.niit.vhr.controller.system;

import com.niit.vhr.model.Hr;
import com.niit.vhr.model.RespBean;
import com.niit.vhr.model.Role;
import com.niit.vhr.service.HrService;
import com.niit.vhr.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-28 21:43
 * @description :
 */
@RestController
@RequestMapping("/system/hr")
@Api(value = "HrController", tags = {"操作员管理"})
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "根据用户名查询操作员")
    @GetMapping("/")
    public RespBean getAllHrs(String keywords) {
        List<Hr> allHrs = hrService.getAllHrs(keywords);
        return RespBean.ok("", allHrs);
    }

    @PutMapping("/status")
    @ApiOperation(value = "更新操作员状态")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHrStatus(hr) == 1) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public RespBean getAllRoles() {
        List<Role> allRoles = roleService.getAllRoles();
        return RespBean.ok("", allRoles);
    }

    @PutMapping("/roles")
    @ApiOperation(value = "更新操作员角色")
    public RespBean updateRole(Integer hrid, Integer[] rids) {
        if (roleService.updateRoles(hrid, rids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除用户")
    public RespBean deleteHr(@PathVariable Integer id) {
        if (hrService.deleteHrById(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");

    }
}
