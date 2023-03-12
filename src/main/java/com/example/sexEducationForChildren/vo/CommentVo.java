package com.example.sexEducationForChildren.vo;

import lombok.Data;

@Data
public class CommentVo {

    private Integer commentId;

    private Integer userId;

    private String nickname;

    private String commentDate;

    private String commentContent;

    private Integer commentFabulous;

    private String userImg;
}
