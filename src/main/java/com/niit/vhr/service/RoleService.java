package com.niit.vhr.service;

import com.niit.vhr.mapper.RoleMapper;
import com.niit.vhr.model.Menu;
import com.niit.vhr.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-26 21:38
 * @description :
 */

@Service
public class RoleService {
    @Resource
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public List<Menu> getAllMenus() {
        return roleMapper.getAllMenus();
    }

    public Integer addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
