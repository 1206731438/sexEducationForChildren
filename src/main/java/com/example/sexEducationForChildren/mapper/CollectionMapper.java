package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.vo.CollectionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectionMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<CollectionVo> selectPage(@Param("search") String search,@Param("userId") Integer userId);

    /**
     * 根据id删除
     * @param collectionId
     * @return
     */
    Integer delById(@Param("collectionId") Integer collectionId);
}
