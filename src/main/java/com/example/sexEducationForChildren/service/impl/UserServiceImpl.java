package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.mapper.UserMapper;
import com.example.sexEducationForChildren.po.LoginPo;
import com.example.sexEducationForChildren.service.UserService;
import com.example.sexEducationForChildren.utils.JWTUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.example.sexEducationForChildren.entity.UserEntity;
import com.example.sexEducationForChildren.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultUtil login(LoginPo loginPo) {
        //根据用户名查询
        List<UserEntity> users = userMapper.getUserByUsername(loginPo.getUsername());
        //不存在提示
        if (users == null || users.size() == 0) {
            return ResultUtil.respinseFail("用户不存在！");
        }
        //如果存在，判断密码是否正确
        UserEntity user = users.get(0);
        //判断加密后的密码是否和查出用户密码一致
        if (DigestUtils.md5DigestAsHex((loginPo.getPassword()+user.getSalt()).getBytes(StandardCharsets.UTF_8)).equals(user.getPassword())) {
            //如果一致，生成token，返回数据
            String token = JWTUtil.createToken(user.getUserId());
            LoginVo returnUser = new LoginVo();
            returnUser.setUsername(user.getUsername());
            returnUser.setPassword(loginPo.getPassword());
            returnUser.setToken(token);
            returnUser.setUserId(user.getUserId());
            return ResultUtil.respinseSuccess("登陆成功",returnUser);
        }
        return ResultUtil.respinseFail("密码错误");
    }


    @Override
    public Integer register(UserEntity user) {
        //根据用户查询用户是否存在
        List<UserEntity> users = userMapper.getUserByUsername(user.getUsername());
        //如果存在，返回
        if (users != null && users.size() != 0) {
            return 0;
        }
        //如果不存在,生成密钥
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        //通过MD5加密密码
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes(StandardCharsets.UTF_8)));
        //存入数据库
        return userMapper.register(user);
    }

    @Override
    public UserEntity selUserById(Integer userId) {
        return userMapper.selUserById(userId);
    }

    @Override
    public Integer updateUserById(UserEntity user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public Integer updateImg(String imgSrc, Integer userId) {
        return userMapper.updateImg(imgSrc,userId);
    }
}
