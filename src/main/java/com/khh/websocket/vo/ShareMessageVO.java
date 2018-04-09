package com.khh.websocket.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/4/9.
 */
public class ShareMessageVO implements Serializable{

    private String sharesNum;

    private String sharesDesc;

    private String openId;

    public String getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(String sharesNum) {
        this.sharesNum = sharesNum;
    }

    public String getSharesDesc() {
        return sharesDesc;
    }

    public void setSharesDesc(String sharesDesc) {
        this.sharesDesc = sharesDesc;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "ShareMessageVO{" +
                "sharesNum='" + sharesNum + '\'' +
                ", sharesDesc='" + sharesDesc + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
