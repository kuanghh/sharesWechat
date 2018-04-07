package com.khh.web.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/4/7.
 */
public class SharesDetailVO extends SharesVO implements Serializable{

    private String sharesId;

    public String getSharesId() {
        return sharesId;
    }

    public void setSharesId(String sharesId) {
        this.sharesId = sharesId;
    }
}
