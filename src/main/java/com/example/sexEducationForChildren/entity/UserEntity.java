package com.example.sexEducationForChildren.entity;

import lombok.Data;

@Data
public class UserEntity {

    private Integer userId;

    private String username;

    private String nickname;

    private String password;

    private String salt;

    private String sex;

    private Integer age;

    private String email;

    private String phone;

    private String introduction;

    private String createDate;

    private String imgSrc;
}
