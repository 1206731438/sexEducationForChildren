package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.mapper.ProjectMapper;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.ProjectService;
import com.example.sexEducationForChildren.vo.ProjectVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<ProjectVo> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<ProjectVo> projectVos = projectMapper.selectPage(page.getSearch());
        for (ProjectVo projectVo : projectVos) {
            String projectImgs = projectVo.getProjectImgs();
            if (projectImgs != null && !projectImgs.equals("")) {
                projectVo.setImgs(Arrays.asList(projectImgs.split(";")));
            }
            String projectContent = projectVo.getProjectContent();
            if (projectContent != null && !projectContent.equals("")) {
                String[] split = projectContent.split(";");
                projectVo.setContents(Arrays.asList(split));
                StringBuilder newContent = new StringBuilder();
                for (String content : split) {
                    if (!content.contains(".jpg")) {
                        newContent.append(content);
                    }
                }
            }
        }
        return new PageInfo<>(projectVos);
    }

    @Override
    public ProjectVo selById(Integer projectId) {
        ProjectVo projectVo = projectMapper.selById(projectId);
        String projectContent = projectVo.getProjectContent();
        if (projectContent != null && !projectContent.equals("")) {
            projectVo.setContents(Arrays.asList(projectContent.split(";")));
        }
        return projectVo;
    }
}
