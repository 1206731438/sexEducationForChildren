package com.example.sexEducationForChildren.publicClass;

import lombok.Data;

@Data
public class Page {

    private Integer currentPage;

    private Integer pageSize;

    private String type;

    private String search;
}
