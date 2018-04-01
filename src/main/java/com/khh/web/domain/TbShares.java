package com.khh.web.domain;

import java.util.Date;

public class TbShares {
    private String id;

    private String sharesNum;

    private String sharesHref;

    private String sharesName;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(String sharesNum) {
        this.sharesNum = sharesNum == null ? null : sharesNum.trim();
    }

    public String getSharesHref() {
        return sharesHref;
    }

    public void setSharesHref(String sharesHref) {
        this.sharesHref = sharesHref == null ? null : sharesHref.trim();
    }

    public String getSharesName() {
        return sharesName;
    }

    public void setSharesName(String sharesName) {
        this.sharesName = sharesName == null ? null : sharesName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}