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
}
