package com.khh.wechat.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 * 群发接口VO
 */
public class MassMessageVO implements Serializable{

    private List<String> touser;
    private String msgtype;
    private TextVO text;


    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public TextVO getText() {
        return text;
    }

    public void setText(TextVO text) {
        this.text = text;
    }
}
