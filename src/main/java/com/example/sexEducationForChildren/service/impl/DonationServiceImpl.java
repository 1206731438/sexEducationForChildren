package com.example.sexEducationForChildren.service.impl;

import com.example.sexEducationForChildren.mapper.DonationMapper;
import com.example.sexEducationForChildren.po.DonationUserPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.service.DonationService;
import com.example.sexEducationForChildren.vo.DonationUserVo;
import com.example.sexEducationForChildren.vo.DonationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationMapper donationMapper;

    @Override
    public PageInfo<DonationVo> selctPage(Page page, Integer userId) {

        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<DonationVo> donationVos = donationMapper.selectPage(page.getSearch(),userId);
        return new PageInfo<>(donationVos);
    }

    @Override
    public Integer delById(Integer donationId) {
        return donationMapper.delById(donationId);
    }

    @Override
    public DonationUserVo selDonationUser() {
        DonationUserVo donationUserVo = donationMapper.selDonationUser();
        List<DonationUserPo> donationUserPos = donationMapper.selDonation();
        if (donationUserPos != null && donationUserPos.size() != 0) {
            donationUserVo.setDonationUserPos(donationUserPos);
        }
        return donationUserVo;
    }
}
