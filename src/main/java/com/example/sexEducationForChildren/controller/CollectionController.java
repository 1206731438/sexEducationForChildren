package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.po.CollectionPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.CollectionService;
import com.example.sexEducationForChildren.utils.JWTUtil;
import com.example.sexEducationForChildren.utils.PageUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.example.sexEducationForChildren.vo.CollectionVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    /**
     * 分页查询
     * @return
     */
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
        PageInfo<CollectionVo> pageInfo = collectionService.selectPage(page,userId);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 删除操作
     * @param request
     * @param collectionPo
     * @return
     */
    @PostMapping("/delById")
    public ResultUtil delById(HttpServletRequest request, @RequestBody CollectionPo collectionPo) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer i = collectionService.delById(collectionPo.getCollectionId());
        if (i == 0) {
            return ResultUtil.respinseFail("删除失败");
        }
        return ResultUtil.respinseSuccess("删除成功");
    }
}
