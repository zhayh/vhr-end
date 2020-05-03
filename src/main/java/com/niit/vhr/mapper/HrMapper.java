package com.niit.vhr.mapper;

import com.niit.vhr.model.Hr;
import com.niit.vhr.model.Role;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    @Select("select * from hr where username=#{username}")
    Hr loadUserByUsername(String username);

    @Select("select r.* from role r, hr_role hrr where hrr.rid=r.id and hrr.hrid=#{id}")
    List<Role> getHrRolesById(Integer id);


    List<Hr> getAllHrs(Integer hrId);

    @Update("update hr set enabled=#{enabled} where id=#{id}")
    int updateStatus(Integer id, Boolean enabled);
}
