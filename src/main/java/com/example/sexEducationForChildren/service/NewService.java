package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.entity.NewEntity;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.vo.CommentVo;
import com.example.sexEducationForChildren.vo.FabulousVo;
import com.example.sexEducationForChildren.vo.NewVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewService {
    /**
     * 分页查询
     * @param page
     * @return
     */
    PageInfo<NewVo> selectPage(Page page);

    /**'
     * 点赞/取消
     * @return
     */
    Integer fabulous(Integer userId,Integer newId);

    /**
     * 收藏
     * @param newId
     * @param userId
     * @return
     */
    Integer collection(Integer newId, Integer userId);

    /**
     * 阅读
     * @param newId
     */
    void read(Integer newId);

    /**
     * 根据id查询
     * @param newId
     * @return
     */
    NewVo selById(Integer newId);

    /**
     * 查询最新的是条新闻
     * @return
     */
    List<NewEntity> selNewTitle();

    /**
     * 查询评论列表
     * @param newId
     * @return
     */
    List<CommentVo> selComment(Integer newId);

    /**
     * 添加评论
     * @param userId
     * @param newId
     * @return
     */
    Integer instComment(Integer userId, Integer newId,String commentContent);

    /**
     * 评论点赞
     * @param commentId
     * @return
     */
    Integer CommentFabulous(Integer commentId);
}
