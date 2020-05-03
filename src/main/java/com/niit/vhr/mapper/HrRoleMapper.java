package com.niit.vhr.mapper;

import com.niit.vhr.model.HrRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    @Delete("delete from hr_role where hrid=#{hrid}")
    void deleteByHrid(Integer hrid);

    int addRole(@Param("hrid") Integer hrid, @Param("rids") Integer[] rids);
}
