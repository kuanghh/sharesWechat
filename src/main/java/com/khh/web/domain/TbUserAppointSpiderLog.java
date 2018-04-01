package com.khh.web.domain;

import java.util.Date;

public class TbUserAppointSpiderLog {
    private String id;

    private String openId;

    private Date startTime;

    private Date endTime;

    private String sharesNum;

    private Integer state;

    private String dataJson;

    private String msgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(String sharesNum) {
        this.sharesNum = sharesNum == null ? null : sharesNum.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson == null ? null : dataJson.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}