package com.shihang.usermerge.usermergeconsmerinsertold.model.dto;

import java.util.Date;

/**
 * 会员积分表
 */
public class CustomerPoint {
    private int id;
    private Integer points;
    private Date updatedOnUtc;
    private Date createdOnUtc;
    private Integer customerId;
    private String customerGuid;
    private String cityFlag;
    private Integer historyPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(Date updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public Date getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(Date createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getHistoryPoints() {
        return historyPoints;
    }

    public void setHistoryPoints(Integer historyPoints) {
        this.historyPoints = historyPoints;
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
