package com.example.sexEducationForChildren.controller;

import com.example.sexEducationForChildren.entity.NewEntity;
import com.example.sexEducationForChildren.po.CommentPo;
import com.example.sexEducationForChildren.po.FabulousPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.NewService;
import com.example.sexEducationForChildren.utils.JWTUtil;
import com.example.sexEducationForChildren.utils.PageUtil;
import com.example.sexEducationForChildren.utils.ResultUtil;
import com.example.sexEducationForChildren.vo.CommentVo;
import com.example.sexEducationForChildren.vo.NewVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/new")
public class NewController {

    @Autowired
    private NewService newService;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/selectPage")
    public ResultUtil selectPage(@RequestBody Page page) {
        PageInfo<NewVo> pageInfo = newService.selectPage(page);
        return ResultUtil.respinseSuccess("查询成功", PageUtil.ResultPage(pageInfo));
    }

    /**
     * 点赞/取消
     * @return
     */
    @PostMapping("/fabulous")
    public ResultUtil fabulous(HttpServletRequest request, @RequestBody FabulousPo fabulousPo) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        Integer i = newService.fabulous(userId,fabulousPo.getNewId());
        if (i == 2) {
            return ResultUtil.respinseSuccess(ResultUtil.HAVE_COLLECTION,"点赞已取消");
        }
        return ResultUtil.respinseSuccess("点赞成功");
    }

    /**
     * 收藏
     * @param request
     * @param fabulousPo
     * @return
     */
    @PostMapping("/collection")
    public ResultUtil collection(HttpServletRequest request,@RequestBody FabulousPo fabulousPo) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        Integer i = newService.collection(fabulousPo.getNewId(),userId);
        if (i == 0) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"您已收藏");
        }
        return ResultUtil.respinseSuccess("收藏成功");
    }

    /**
     * 阅读
     * @param fabulousPo
     * @return
     */
    @PostMapping("/read")
    public ResultUtil read(@RequestBody FabulousPo fabulousPo) {
        newService.read(fabulousPo.getNewId());
        return ResultUtil.respinseSuccess();
    }

    /**
     * 根据id查询
     * @param fabulousPo
     * @return
     */
    @PostMapping("/selById")
    public ResultUtil selById(@RequestBody FabulousPo fabulousPo) {
        NewVo newVo = newService.selById(fabulousPo.getNewId());
        return ResultUtil.respinseSuccess("查询成功",newVo);
    }

    /**
     * 查询新闻最新的10条新闻
     * @return
     */
    @PostMapping("/selNewTitle")
    public ResultUtil selNewTitle() {
        List<NewEntity> news = newService.selNewTitle();
        return ResultUtil.respinseSuccess("查询成功",news);
    }

    /**
     * 查询评论
     * @return
     */
    @PostMapping("/selComment")
    public ResultUtil selComment(@RequestBody CommentPo commentPo) {
        List<CommentVo> commentVos = newService.selComment(commentPo.getNewId());
        return ResultUtil.respinseSuccess("查询成功",commentVos);
    }

    /**
     * 添加评论
     * @param commentPo
     * @return
     */
    @PostMapping("/instComment")
    public ResultUtil instComment(HttpServletRequest request,@RequestBody CommentPo commentPo) {
        String token = request.getHeader("token");
        if (token == null || token.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"您未登录，请先登录");
        }
        //判断token是否过期
        if (!JWTUtil.overTime(token)) {
            return ResultUtil.respinseFail(ResultUtil.LOGIN_OUT_TIME,"token已过期");
        }
        Integer userId = JWTUtil.verifyToken(token);
        Integer i = newService.instComment(userId,commentPo.getNewId(),commentPo.getCommentContent());
        if (i == 0) {
            return ResultUtil.respinseFail("评论失败");
        }
        return ResultUtil.respinseSuccess("评论成功");
    }

    /**
     * 评论点赞
     * @return
     */
    @PostMapping("/CommentFabulous")
    public ResultUtil CommentFabulous(@RequestBody CommentPo commentPo) {
        Integer i = newService.CommentFabulous(commentPo.getCommentId());
        if (i == 0) {
            return ResultUtil.respinseFail("点赞失败");
        }
        return ResultUtil.respinseSuccess("点赞成功");
    }
}
