package com.shihang.usermerge.usermergeconsmerinsertold.mapper;

import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.CustomerPoint;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.CustomerPointList;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.UserPointDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangzhuangnai
 * @date 2021/3/23
 * @since 1.0.0
 **/
@Repository
public interface PointMapper {

    List<UserPointDTO> findUserPoint(List<String> mainGuid);


    /**
     * @param customerGuid
     * @return
     */
    CustomerPoint findCustomerPoint(String customerGuid);

    /**
     * @param customerPointList
     */
    int pointDetail(CustomerPointList customerPointList);

    /**
     * 减库的分数
     *
     * @param points       积分数
     * @param customerGuid
     * @return
     */
    int delPoints(@Param("points") int points,
                  @Param("customerGuid") String customerGuid);

    /**
     *
     * @param points
     * @param customerGuid
     */
    int addPoints(@Param("points") int points,
                   @Param("customerGuid") String customerGuid);
}
