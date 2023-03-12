package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.mapper.CollectionMapper;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.CollectionService;
import com.example.sexEducationForChildren.vo.CollectionVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public PageInfo<CollectionVo> selectPage(Page page,Integer userId) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<CollectionVo> collectionVos = collectionMapper.selectPage(page.getSearch(),userId);
        for (CollectionVo collectionVo : collectionVos) {
            String newContent = collectionVo.getNewContent();
            if (newContent != null && !newContent.equals("")) {
                String[] split = newContent.split(";");
                collectionVo.setContents(Arrays.asList(split));
                StringBuilder sb = new StringBuilder("");
                for (String s : split) {
                    if (!s.contains(".jpg")) {
                        sb.append(s);
                    }
                }
                collectionVo.setNewContent(sb.toString());
            }
            String newImgs = collectionVo.getNewImgs();
            if (newImgs != null && !newImgs.equals("")) {
                collectionVo.setImgs(Arrays.asList(newImgs.split(";")));
            }
        }
        return new PageInfo<>(collectionVos);
    }

    @Override
    public Integer delById(Integer collectionId) {
        return collectionMapper.delById(collectionId);
    }
}
