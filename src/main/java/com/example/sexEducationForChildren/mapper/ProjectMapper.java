package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.vo.ProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<ProjectVo> selectPage(@Param("search") String search);

    /**
     * 根据id查询
     * @param projectId
     * @return
     */
    ProjectVo selById(@Param("projectId") Integer projectId);
}
