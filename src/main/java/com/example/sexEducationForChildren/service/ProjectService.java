package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.vo.ProjectVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<ProjectVo> selectPage(Page page);

    /**
     * 根据id查询
     * @param projectId
     * @return
     */
    ProjectVo selById(Integer projectId);
}
