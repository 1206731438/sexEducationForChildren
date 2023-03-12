package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.vo.CollectionVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface CollectionService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<CollectionVo> selectPage(Page page,Integer userId);

    /**
     * 根据id删除
     * @param collectionId
     * @return
     */
    Integer delById(Integer collectionId);
}
