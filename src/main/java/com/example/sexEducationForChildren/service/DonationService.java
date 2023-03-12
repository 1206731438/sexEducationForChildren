package com.example.sexEducationForChildren.service;

import com.example.sexEducationForChildren.po.DonationUserPo;
import com.example.sexEducationForChildren.publicClass.Page;
import com.example.sexEducationForChildren.vo.DonationUserVo;
import com.example.sexEducationForChildren.vo.DonationVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationService {
    /**
     * 分页查询
     * @param page
     * @param userId
     * @return
     */
    PageInfo<DonationVo> selctPage(Page page, Integer userId);

    /**
     * 根据id删除
     * @param donationId
     * @return
     */
    Integer delById(Integer donationId);

    /**
     * 查询捐赠人列表
     * @return
     */
    DonationUserVo selDonationUser();
}
