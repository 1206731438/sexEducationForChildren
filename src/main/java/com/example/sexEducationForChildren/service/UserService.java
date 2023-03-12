package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.entity.UserEntity;
import com.example.sexEducationForChildren.po.LoginPo;
import com.example.sexEducationForChildren.utils.ResultUtil;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 用户登录
     * @param loginPo
     * @return
     */
    ResultUtil login(LoginPo loginPo);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Integer register(UserEntity user);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    UserEntity selUserById(Integer userId);

    /**
     * 根据id修改信息
     * @param user
     * @return
     */
    Integer updateUserById(UserEntity user);

    /**
     * 修改头像
     * @param imgSrc
     * @param userId
     * @return
     */
    Integer updateImg(String imgSrc, Integer userId);
}
