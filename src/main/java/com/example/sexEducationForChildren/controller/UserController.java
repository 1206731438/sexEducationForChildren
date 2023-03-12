package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.entity.UserEntity;
import com.example.sexEducationForChildren.po.LoginPo;
import com.example.sexEducationForChildren.service.UserService;
import com.example.sexEducationForChildren.utils.JWTUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param loginPo
     * @return
     */
    @PostMapping("/login")
    public ResultUtil login(@RequestBody LoginPo loginPo) {
        if (loginPo.getUsername() == null || loginPo.getUsername().equals("")) {
            return ResultUtil.respinseFail("用户名不能为空！");
        }else if (loginPo.getPassword() == null || loginPo.getPassword().equals("")){
            return ResultUtil.respinseFail("密码不能为空！");
        }
        return userService.login(loginPo);
    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResultUtil register(@RequestBody UserEntity user) {
        if (user.getUsername() == null || user.getUsername().equals("")) {
            return ResultUtil.respinseFail("用户名不能为空");
        } else if (user.getPassword() == null || user.getPassword().equals("")) {
            return ResultUtil.respinseFail("密码不能为空");
        }
        Integer i = userService.register(user);
        if (i == 0) {
            return ResultUtil.respinseFail("注册失败，用户名已存在");
        }
        return ResultUtil.respinseSuccess("注册成功");
    }

    @PostMapping("/selUserById")
    public ResultUtil selUserById(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        UserEntity user = userService.selUserById(userId);
        return ResultUtil.respinseSuccess("查询成功",user);
    }

    @PostMapping("/updateUserById")
    public ResultUtil updateUserById(HttpServletRequest request,@RequestBody UserEntity user) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        user.setUserId(userId);
        Integer i = userService.updateUserById(user);
        if (i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }

    /**
     * 图片上传
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // 定义存储图片的地址
        String folder = "D:/project/vueProject/sex-education-for-children/src/imgs";
        // 文件夹
        File imgFolder = new File(folder);
        // 获取文件名
        String fname = file.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + fname.substring(fname.lastIndexOf(".")+1);
        // 获取时间字符串
        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        // 生成新的文件名
        String newFileName = dateTimeFormatter + UUID.randomUUID().toString().replaceAll("-","") + ext;
        // 文件在本机的全路径
        File filePath = new File(imgFolder, newFileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            file.transferTo(filePath);
            // 返回文件名
            return filePath.getName();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 修改头像
     * @return
     */
    @PostMapping("/updateImg")
    public ResultUtil updateImg(HttpServletRequest request,@RequestBody UserEntity user) {
        if (user.getImgSrc() == null || user.getImgSrc().equals("")) {
            return ResultUtil.respinseFail("请先上传图片");
        }
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        user.setUserId(userId);
        Integer i = userService.updateImg(user.getImgSrc(),userId);
        if(i == 0) {
            return ResultUtil.respinseFail("修改失败");
        }
        return ResultUtil.respinseSuccess("修改成功");
    }
}
