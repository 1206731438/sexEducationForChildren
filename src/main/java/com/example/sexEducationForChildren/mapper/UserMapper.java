package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.po.LoginPo;
import com.example.sexEducationForChildren.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    List<UserEntity> getUserByUsername(@Param("username") String username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Integer register(@Param("user") UserEntity user);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    UserEntity selUserById(@Param("userId") Integer userId);

    /**
     * 根据id修改信息
     * @param user
     * @return
     */
    Integer updateUserById(@Param("user") UserEntity user);

    /**
     * 修改头像
     * @param imgSrc
     * @param userId
     * @return
     */
    Integer updateImg(@Param("imgSrc") String imgSrc,@Param("userId") Integer userId);
}
