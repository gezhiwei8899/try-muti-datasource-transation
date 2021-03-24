package com.shihang.usermerge.usermergeconsmerinsertold.model.dto;

/**
 * @author jiangzhuangnai
 * @date 2021/3/23
 * @since 1.0.0
 **/
public class UserPointDTO {

    String customerGuid;

    int customerId;

    String cityFlag;

    int points;

    public String getCustomerGuid() {
        return customerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        this.customerGuid = customerGuid;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String cityFlag) {
        this.cityFlag = cityFlag;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
