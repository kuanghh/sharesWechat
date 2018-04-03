package com.khh.web.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/4/2.
 * 调用实时爬虫的vo
 */
public class CallSharesVO implements Serializable{

    private String url;
    private String open_id;
    private String shares_num;
    private String msg_id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getShares_num() {
        return shares_num;
    }

    public void setShares_num(String shares_num) {
        this.shares_num = shares_num;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

}

