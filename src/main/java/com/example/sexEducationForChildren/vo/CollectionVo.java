package com.example.sexEducationForChildren.vo;

import lombok.Data;

import java.util.List;

@Data
public class CollectionVo {

    private Integer collectionId;

    private Integer newId;

    private String newTitle;

    private String newContent;

    private List<String> contents;

    private String newImgs;

    private List<String> imgs;

    private String newType;

}
