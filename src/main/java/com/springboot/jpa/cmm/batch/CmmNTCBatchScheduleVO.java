package com.springboot.jpa.cmm.batch;

import java.util.Date;

public class CmmNTCBatchScheduleVO {
    private String batchId;
    private String beginDttm;
    private String endDttm;
    private long intervalMillis;
    private String intervalYn;
    private String enableYn;
    private String serviceTimeYn;
    private Date lastExcuDttm;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBeginDttm() {
        return beginDttm;
    }

    public void setBeginDttm(String beginDttm) {
        this.beginDttm = beginDttm;
    }

    public String getEndDttm() {
        return endDttm;
    }

    public void setEndDttm(String endDttm) {
        this.endDttm = endDttm;
    }

    public long getIntervalMillis() {
        return intervalMillis;
    }

    public void setIntervalMillis(long intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    public String getIntervalYn() {
        return intervalYn;
    }

    public void setIntervalYn(String intervalYn) {
        this.intervalYn = intervalYn;
    }

    public String getEnableYn() {
        return enableYn;
    }

    public void setEnableYn(String enableYn) {
        this.enableYn = enableYn;
    }

    public String getServiceTimeYn() {
        return serviceTimeYn;
    }

    public void setServiceTimeYn(String serviceTimeYn) {
        this.serviceTimeYn = serviceTimeYn;
    }

    public Date getLastExcuDttm() {
        return lastExcuDttm;
    }

    public void setLastExcuDttm(Date lastExcuDttm) {
        this.lastExcuDttm = lastExcuDttm;
    }
}
