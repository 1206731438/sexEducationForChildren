package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.entity.SecurityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SecurityMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<SecurityEntity> selectPage(@Param("search") String search);
}
