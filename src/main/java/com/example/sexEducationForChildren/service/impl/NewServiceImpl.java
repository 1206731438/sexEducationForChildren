package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.entity.NewEntity;
import com.example.sexEducationForChildren.mapper.NewMapper;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.NewService;
import com.example.sexEducationForChildren.vo.CommentVo;
import com.example.sexEducationForChildren.vo.NewVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    private NewMapper newMapper;

    @Override
    public PageInfo<NewVo> selectPage(Page page) {
        if (page.getCurrentPage() != null || page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<NewVo> newEntities = newMapper.selectPage(page.getType(),page.getSearch());

        for (NewVo newEntity : newEntities) {
            if (newEntity.getNewContent() != null && !newEntity.getNewContent().equals("")) {
                String[] contents = newEntity.getNewContent().split(";");
                newEntity.setContents(Arrays.asList(contents));
                StringBuilder newContent = new StringBuilder();
                for (String content : contents) {
                    if (!content.contains(".jpg")) {
                        newContent.append(content);
                    }
                }
                newEntity.setNewContent(newContent.toString());
            }
            if (newEntity.getNewImgs() != null && !newEntity.getNewImgs().equals("")) {
                String[] imgs = newEntity.getNewImgs().split(";");
                newEntity.setImgs(Arrays.asList(imgs));
            }
        }
        return new PageInfo(newEntities);
    }

    @Override
    public Integer fabulous(Integer userId,Integer newId) {
        //先查询是否已经点赞
        List<Integer> fabulousId = newMapper.selFabulousByUserIdAndNewId(userId,newId);
        if (fabulousId != null && fabulousId.size() != 0) {
            newMapper.delFabulousById(fabulousId.get(0));
            return 2;
        }
        newMapper.instFabulous(userId,newId);
        return 1;
    }

    @Override
    public Integer collection(Integer newId, Integer userId) {
        //根据用户id和新闻id查询是否已经收藏
        List<Integer> collectionId = newMapper.selCollByUserIdAndNewId(userId,newId);
        if (collectionId != null && collectionId.size() != 0) {
            return 0;
        }
        return newMapper.instCollection(userId,newId);
    }

    @Override
    public void read(Integer newId) {
        newMapper.read(newId);
    }

    @Override
    public NewVo selById(Integer newId) {
        NewVo newVo = newMapper.selById(newId);
        if (newVo.getNewContent() != null && !newVo.getNewContent().equals("")) {
            newVo.setContents(Arrays.asList(newVo.getNewContent().split(";")));
        }
        if (newVo.getNewImgs() != null && !newVo.getNewImgs().equals("")) {
            newVo.setImgs(Arrays.asList(newVo.getNewImgs().split(";")));
        }
        return newVo;
    }

    @Override
    public List<NewEntity> selNewTitle() {
        return newMapper.selNewTitle();
    }

    @Override
    public List<CommentVo> selComment(Integer newId) {
        return newMapper.selComment(newId);
    }

    @Override
    public Integer instComment(Integer userId, Integer newId,String commentContent) {
        return newMapper.instComment(userId,newId,commentContent);
    }

    @Override
    public Integer CommentFabulous(Integer commentId) {
        return newMapper.CommentFabulous(commentId);
    }
}
