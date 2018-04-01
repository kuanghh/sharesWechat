package com.khh.web.domain;

import java.util.Date;

public class TbSpiderLog {


    public static Integer STATE_FAILED = 0;
    public static Integer STATE_SUCCESS = 1;

    private String id;

    private Date startTime;

    private Date endTime;

    private Integer state;

    private Date startDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }
}