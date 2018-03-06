package com.khh.wechat.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/3/6.
 * 专门记录
 * signature
 * timestamp
 * nonce
 * echostr
 */
public class WechatVO implements Serializable{

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}
