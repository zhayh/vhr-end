package com.niit.vhr.mapper;

import com.niit.vhr.model.JobLevel;
import com.niit.vhr.model.Position;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    @Select("select * from joblevel")
    List<JobLevel> selectAllJobLevel();

//    @Delete({"<script> ",
//        "delete from joblevel where id in ",
//        "<foreach collection='array' item='item' open='('  separator=',' close=')'>",
//        "#{item}",
//        "</foreach>",
//        "</script>" })
    Integer deleteByIds(Integer[] ids);
}
