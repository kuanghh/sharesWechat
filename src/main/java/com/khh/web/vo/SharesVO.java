package com.khh.web.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2018/4/1.
 */
public class SharesVO implements Serializable , SharesInterface{

    private String sharesNum;

    private String sharesHref;

    private String sharesName;

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

    private Date createTime;

    public String getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(String sharesNum) {
        this.sharesNum = sharesNum;
    }

    public String getSharesHref() {
        return sharesHref;
    }

    public void setSharesHref(String sharesHref) {
        this.sharesHref = sharesHref;
    }

    public String getSharesName() {
        return sharesName;
    }

    public void setSharesName(String sharesName) {
        this.sharesName = sharesName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
