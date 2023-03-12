package com.example.sexEducationForChildren.entity;

import lombok.Data;

@Data
public class AliPay {
    private Integer userId;
    private Integer projectId;
    private double totalAmount;
//    private String subject;
    private String alipayTraceNo;
}
