package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.entity.SecurityEntity;
import com.example.sexEducationForChildren.mapper.SecurityMapper;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.SecurityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityMapper securityMapper;

    @Override
    public PageInfo<SecurityEntity> selectPage(Page page) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<SecurityEntity> securityEntities = securityMapper.selectPage(page.getSearch());
        return new PageInfo<>(securityEntities);
    }
}
