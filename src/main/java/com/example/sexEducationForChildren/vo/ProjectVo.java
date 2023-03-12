package com.example.sexEducationForChildren.vo;

import com.example.sexEducationForChildren.entity.ProjectEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProjectVo extends ProjectEntity {

    private List<String> imgs;

    private List<String> contents;
}
