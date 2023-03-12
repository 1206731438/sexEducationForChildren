package com.example.sexEducationForChildren.mapper;

import com.example.sexEducationForChildren.po.DonationUserPo;
import com.example.sexEducationForChildren.vo.DonationUserVo;
import com.example.sexEducationForChildren.vo.DonationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DonationMapper {
    /**
     * 分页查询
     * @param search
     * @param userId
     * @return
     */
    List<DonationVo> selectPage(@Param("search") String search,@Param("userId") Integer userId);

    /**
     * 根据id删除
     * @param donationId
     * @return
     */
    Integer delById(@Param("donationId") Integer donationId);

    /**'
     * 查询捐赠人总金额
     * @return
     */
    DonationUserVo selDonationUser();

    /**
     * 查询捐赠人列表
     * @return
     */
    List<DonationUserPo> selDonation();

    /**
     * 根据projectId和userId查询
     * @param projectId
     * @param userId
     * @return
     */
    List<Integer> selDonationById(@Param("projectId") Integer projectId,@Param("userId") Integer userId);

    /**
     * 修改
     * @param donationId
     * @param money
     */
    void updateMoney(@Param("donationId") Integer donationId,@Param("money") Double money);

    /**
     * 添加
     * @param userId
     * @param projectId
     * @param money
     */
    void inst(@Param("userId") Integer userId,@Param("projectId") Integer projectId,@Param("money") Double money);
}
