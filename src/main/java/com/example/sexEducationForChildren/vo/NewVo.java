package com.example.sexEducationForChildren.vo;

import com.example.sexEducationForChildren.entity.NewEntity;
import lombok.Data;

import java.util.List;

@Data
public class NewVo extends NewEntity {

    private List<String> imgs;

    private List<String> contents;
}
