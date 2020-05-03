package com.niit.vhr.service;

import com.niit.vhr.mapper.HrRoleMapper;
import com.niit.vhr.mapper.RoleMapper;
import com.niit.vhr.model.Menu;
import com.niit.vhr.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    HrRoleMapper hrRoleMapper;

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

    @Transactional
    public boolean updateRoles(Integer hrid, Integer[] rids) {
        hrRoleMapper.deleteByHrid(hrid);
        return hrRoleMapper.addRole(hrid, rids) == rids.length;
    }
}
