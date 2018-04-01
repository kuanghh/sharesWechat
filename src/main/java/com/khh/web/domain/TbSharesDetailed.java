package com.khh.web.domain;


import com.khh.web.vo.SharesInterface;

import java.util.Date;

public class TbSharesDetailed implements SharesInterface{
    private String id;

    private String sharesId;

    private Date createTime;

    private Integer openPrice;

    private Integer closePrice;

    private Integer ceillingPrice;

    private Integer floorPrice;

    private Integer riseAndFallRange;

    private Integer riseAndFallQuota;

    private Integer volume;

    private Integer turnVolume;

    private Integer turnoverRate;

    private Integer amplitude;

    private Integer pERatio;

    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSharesId() {
        return sharesId;
    }

    public void setSharesId(String sharesId) {
        this.sharesId = sharesId == null ? null : sharesId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Integer openPrice) {
        this.openPrice = openPrice;
    }

    public Integer getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Integer closePrice) {
        this.closePrice = closePrice;
    }

    public Integer getCeillingPrice() {
        return ceillingPrice;
    }

    public void setCeillingPrice(Integer ceillingPrice) {
        this.ceillingPrice = ceillingPrice;
    }

    public Integer getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Integer floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getRiseAndFallRange() {
        return riseAndFallRange;
    }

    public void setRiseAndFallRange(Integer riseAndFallRange) {
        this.riseAndFallRange = riseAndFallRange;
    }

    public Integer getRiseAndFallQuota() {
        return riseAndFallQuota;
    }

    public void setRiseAndFallQuota(Integer riseAndFallQuota) {
        this.riseAndFallQuota = riseAndFallQuota;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getTurnVolume() {
        return turnVolume;
    }

    public void setTurnVolume(Integer turnVolume) {
        this.turnVolume = turnVolume;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Integer amplitude) {
        this.amplitude = amplitude;
    }

    public Integer getpERatio() {
        return pERatio;
    }

    public void setpERatio(Integer pERatio) {
        this.pERatio = pERatio;
    }

    public Integer getPERatio() {
        return pERatio;
    }

    public void setPERatio(Integer pERatio) {
        this.pERatio = pERatio;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}