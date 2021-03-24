package com.shihang.usermerge.usermergeconsmerinsertold.help;

import com.shihang.usermerge.usermergeconsmerinsertold.mapper.PointMapper;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.CustomerPoint;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.CustomerPointList;
import com.shihang.usermerge.usermergeconsmerinsertold.model.dto.UserPointDTO;

import java.util.Date;
import java.util.UUID;

/**
 * @author jiangzhuangnai
 * @date 2021/3/24
 * @since 1.0.0
 **/
public class CustomerPointWrapper {


    private final PointMapper mapper;


    public CustomerPointWrapper(PointMapper mapper) {
        this.mapper = mapper;
    }

    public void delPoints(UserPointDTO userPointDTO, int points) {

        mapper.delPoints(points, userPointDTO.getCustomerGuid());


        CustomerPoint newCustomerPoint = mapper.findCustomerPoint(userPointDTO.getCustomerGuid());

        int remainPoint = newCustomerPoint.getPoints();
        int priorPoint = remainPoint + userPointDTO.getPoints();

        int remainHistoryPoint = newCustomerPoint.getHistoryPoints();
        int historyPoint = remainHistoryPoint + points;

        //创建积分明细
        CustomerPointList customerPointList = new CustomerPointList();
        customerPointList.setCustomerId(userPointDTO.getCustomerId());
        customerPointList.setOprateCustomerId(Integer.MAX_VALUE);
        customerPointList.setCustomerPointMonthCollectID(0);
        customerPointList.setCreatedOnUtc(new Date());
        customerPointList.setRemark("账号迁移");
        customerPointList.setOrderID(0L);
        customerPointList.setOptionName("支出");
        customerPointList.setSubOptionName("账号合并");
        customerPointList.setAmountPoint(points);
        customerPointList.setPriorPoint(priorPoint);
        customerPointList.setRemainPoint(remainPoint);
        customerPointList.setPriorMonthPoint(0);
        customerPointList.setRemainMonthPoint(0);
        customerPointList.setPriorHistoryPoint(historyPoint);
        customerPointList.setRemainHistoryPoint(remainHistoryPoint);
        customerPointList.setCustomerPointId(0);
        customerPointList.setSourceNumber(UUID.randomUUID().toString().replaceAll("-", ""));
        customerPointList.setCustomerGuid(userPointDTO.getCustomerGuid());
        customerPointList.setCityFlag(userPointDTO.getCityFlag());
        customerPointList.setSourceType(21);
        mapper.pointDetail(customerPointList);
    }

    public void addPoints(UserPointDTO mainUserPointDTO, int points) {

        mapper.addPoints(points, mainUserPointDTO.getCustomerGuid());


        CustomerPoint newCustomerPoint = mapper.findCustomerPoint(mainUserPointDTO.getCustomerGuid());

        int remainPoint = newCustomerPoint.getPoints();
        int priorPoint = remainPoint - mainUserPointDTO.getPoints();

        int remainHistoryPoint = newCustomerPoint.getHistoryPoints();
        int historyPoint = remainHistoryPoint - points;

        //创建积分明细
        CustomerPointList customerPointList = new CustomerPointList();
        customerPointList.setCustomerId(mainUserPointDTO.getCustomerId());
        customerPointList.setOprateCustomerId(Integer.MAX_VALUE);
        customerPointList.setCustomerPointMonthCollectID(0);
        customerPointList.setCreatedOnUtc(new Date());
        customerPointList.setRemark("账号迁移");
        customerPointList.setOrderID(0L);
        customerPointList.setOptionName("支出");
        customerPointList.setSubOptionName("账号合并");
        customerPointList.setAmountPoint(points);
        customerPointList.setPriorPoint(priorPoint);
        customerPointList.setRemainPoint(remainPoint);
        customerPointList.setPriorMonthPoint(0);
        customerPointList.setRemainMonthPoint(0);
        customerPointList.setPriorHistoryPoint(historyPoint);
        customerPointList.setRemainHistoryPoint(remainHistoryPoint);
        customerPointList.setCustomerPointId(0);
        customerPointList.setSourceNumber(UUID.randomUUID().toString().replaceAll("-", ""));
        customerPointList.setCustomerGuid(mainUserPointDTO.getCustomerGuid());
        customerPointList.setCityFlag(mainUserPointDTO.getCityFlag());
        customerPointList.setSourceType(21);
        mapper.pointDetail(customerPointList);
    }

    public void delOldPoints(UserPointDTO cityPoint, int points, String cityFlag) {
    }

    public void addOldPoints(UserPointDTO mainPointDTO, int points, String cityFlag) {

    }
}
