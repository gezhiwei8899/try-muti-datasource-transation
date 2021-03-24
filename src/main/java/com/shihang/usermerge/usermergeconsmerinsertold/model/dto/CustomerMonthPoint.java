package com.shihang.usermerge.usermergeconsmerinsertold.model.dto;

import java.util.Date;

/**
 * 会员月度积分表
 */
public class CustomerMonthPoint {
    private int id;
    private int customerId;
    private String customerGuid;
    private String cityFlag;
    private Date createdOnUtc;
    private Date updatedOnUtc;
    private int points;
    private String month;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(Date createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public Date getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(Date updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCustomerGuid() {
        return customerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        this.customerGuid = customerGuid;
    }

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String cityFlag) {
        this.cityFlag = cityFlag;
    }
}
