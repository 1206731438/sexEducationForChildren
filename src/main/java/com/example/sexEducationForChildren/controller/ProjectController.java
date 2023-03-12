package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.po.ProjectPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.ProjectService;
import com.example.sexEducationForChildren.utils.PageUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.example.sexEducationForChildren.vo.ProjectVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welfare")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/selectPage")
    public ResultUtil selectPage(@RequestBody Page page) {
        PageInfo<ProjectVo> pageInfo = projectService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    @PostMapping("/selById")
    public ResultUtil selById(@RequestBody ProjectPo projectPo) {
        ProjectVo projectVo = projectService.selById(projectPo.getProjectId());
        return ResultUtil.respinseSuccess("查询成功",projectVo);
    }
}
