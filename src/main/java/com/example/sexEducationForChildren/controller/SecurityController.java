package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.entity.SecurityEntity;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.SecurityService;
import com.example.sexEducationForChildren.utils.PageUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/selectPage")
    public ResultUtil selectPage(@RequestBody Page page) {
        PageInfo<SecurityEntity> pageInfo = securityService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }
}
