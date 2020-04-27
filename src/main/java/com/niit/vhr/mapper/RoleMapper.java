package com.niit.vhr.mapper;

import com.niit.vhr.model.Menu;
import com.niit.vhr.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    @Select("select * from role")
    List<Role> getAllRoles();

    List<Menu> getAllMenus();
}
