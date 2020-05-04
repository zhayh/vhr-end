package com.niit.vhr.mapper;

import com.niit.vhr.model.Position;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    @Select("select * from position")
    List<Position> selectAllPosition();

//    @Delete({"<script> ",
//            "delete from position where id in ",
//            "<foreach collection='array' item='item' open='('  separator=',' close=')'>",
//            "#{item}",
//            "</foreach>",
//            "</script>" })
    Integer deleteByIds(Integer[] ids);

    Integer batchInsert(List<Position> positions);
}
