package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.entity.NewEntity;
import com.example.sexEducationForChildren.vo.CommentVo;
import com.example.sexEducationForChildren.vo.NewVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewMapper {
    /**
     * 分页查询
     * @param type
     * @return
     */
    List<NewVo> selectPage(@Param("type") String type,@Param("search") String search);

    /**
     * 根据用户id和新闻id查询
     * @param userId
     * @param newId
     * @return
     */
    List<Integer> selFabulousByUserIdAndNewId(@Param("userId") Integer userId,@Param("newId") Integer newId);

    /**
     * 取消点赞
     * @param fabulousId
     */
    void delFabulousById(@Param("fabulousId") Integer fabulousId);

    /**
     * 点赞
     * @param userId
     * @param newId
     */
    void instFabulous(@Param("userId") Integer userId,@Param("newId") Integer newId);

    /**
     * 根据用户id和新闻id查询是否已经收藏
     * @param userId
     * @param newId
     * @return
     */
    List<Integer> selCollByUserIdAndNewId(@Param("userId") Integer userId,@Param("newId") Integer newId);

    /**
     * 添加收藏
     * @param userId
     * @param newId
     * @return
     */
    Integer instCollection(@Param("userId") Integer userId,@Param("newId") Integer newId);

    /**
     * 增加阅读
     * @param newId
     */
    void read(@Param("newId") Integer newId);

    /**
     * 根据id查询
     * @param newId
     * @return
     */
    NewVo selById(@Param("newId") Integer newId);

    /**
     * 查询最新的10条新闻
     * @return
     */
    List<NewEntity> selNewTitle();

    /**
     * 查询评论列表
     * @param newId
     * @return
     */
    List<CommentVo> selComment(@Param("newId") Integer newId);

    /**
     * 添加评论
     * @param userId
     * @param newId
     * @param commentContent
     * @return
     */
    Integer instComment(@Param("userId") Integer userId,@Param("newId") Integer newId,@Param("commentContent") String commentContent);

    /**
     * 评论点赞
     * @param commentId
     * @return
     */
    Integer CommentFabulous(@Param("commentId") Integer commentId);
}
