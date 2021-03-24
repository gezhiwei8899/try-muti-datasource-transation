package com.shihang.usermerge.usermergeconsmerinsertold.model.dto;

import java.util.Date;

/**
 * 会员积分明细表
 */
public class CustomerPointList {
    private int id;
    private Integer customerPointId;
    private Integer customerId;
    private String customerGuid;
    private String cityFlag;
    private Date createdOnUtc;
    private String remark;
    private Long orderID;
    private String optionName;
    private String subOptionName;
    private Integer amountPoint;
    private Integer priorPoint;
    private Integer remainPoint;
    private Integer customerPointMonthCollectID;
    private Integer priorMonthPoint;
    private Integer remainMonthPoint;
    private Integer priorHistoryPoint;
    private Integer remainHistoryPoint;
    private Integer oprateCustomerId;

    private String sourceNumber;
    private Integer sourceType;

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

    public Integer getCustomerPointId() {
        return customerPointId;
    }

    public void setCustomerPointId(Integer customerPointId) {
        this.customerPointId = customerPointId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(Date createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getSubOptionName() {
        return subOptionName;
    }

    public void setSubOptionName(String subOptionName) {
        this.subOptionName = subOptionName;
    }

    public Integer getAmountPoint() {
        return amountPoint;
    }

    public void setAmountPoint(Integer amountPoint) {
        this.amountPoint = amountPoint;
    }

    public Integer getPriorPoint() {
        return priorPoint;
    }

    public void setPriorPoint(Integer priorPoint) {
        this.priorPoint = priorPoint;
    }

    public Integer getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(Integer remainPoint) {
        this.remainPoint = remainPoint;
    }

    public Integer getCustomerPointMonthCollectID() {
        return customerPointMonthCollectID;
    }

    public void setCustomerPointMonthCollectID(Integer customerPointMonthCollectID) {
        this.customerPointMonthCollectID = customerPointMonthCollectID;
    }

    public Integer getPriorMonthPoint() {
        return priorMonthPoint;
    }

    public void setPriorMonthPoint(Integer priorMonthPoint) {
        this.priorMonthPoint = priorMonthPoint;
    }

    public Integer getRemainMonthPoint() {
        return remainMonthPoint;
    }

    public void setRemainMonthPoint(Integer remainMonthPoint) {
        this.remainMonthPoint = remainMonthPoint;
    }

    public Integer getPriorHistoryPoint() {
        return priorHistoryPoint;
    }

    public void setPriorHistoryPoint(Integer priorHistoryPoint) {
        this.priorHistoryPoint = priorHistoryPoint;
    }

    public Integer getRemainHistoryPoint() {
        return remainHistoryPoint;
    }

    public void setRemainHistoryPoint(Integer remainHistoryPoint) {
        this.remainHistoryPoint = remainHistoryPoint;
    }

    public Integer getOprateCustomerId() {
        return oprateCustomerId;
    }

    public void setOprateCustomerId(Integer oprateCustomerId) {
        this.oprateCustomerId = oprateCustomerId;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
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
