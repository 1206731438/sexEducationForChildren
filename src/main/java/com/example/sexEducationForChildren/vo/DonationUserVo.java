package com.example.sexEducationForChildren.vo;

import com.example.sexEducationForChildren.po.DonationUserPo;
import lombok.Data;

import java.util.List;

@Data
public class DonationUserVo {

    private String totalMoney;

    private List<DonationUserPo> donationUserPos;
}
