package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.po.DonationPo;
import com.example.sexEducationForChildren.po.DonationUserPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.DonationService;
import com.example.sexEducationForChildren.utils.JWTUtil;
import com.example.sexEducationForChildren.utils.PageUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.example.sexEducationForChildren.vo.DonationUserVo;
import com.example.sexEducationForChildren.vo.DonationVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/selectPage")
    public ResultUtil selectPage(HttpServletRequest request, @RequestBody Page page) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        PageInfo<DonationVo> pageInfo = donationService.selctPage(page,userId);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    @PostMapping("/delById")
    public ResultUtil delById(HttpServletRequest request,@RequestBody DonationPo donationPo) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);

        Integer i = donationService.delById(donationPo.getDonationId());
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }

    @PostMapping("/selDonationUser")
    public ResultUtil selDonationUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        DonationUserVo donationUserPos = donationService.selDonationUser();
        return ResultUtil.respinseSuccess("查询成功",donationUserPos);
    }
}
