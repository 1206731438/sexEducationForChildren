package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.entity.SecurityEntity;
import com.example.sexEducationForChildren.publicClass.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<SecurityEntity> selectPage(Page page);
}
