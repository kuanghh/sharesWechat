package com.khh.wechat.exception;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 */
public enum WechatExceptionEnum {
    button_key_null("10001","按钮没有key值");

    private String code;

    private String desc;

    WechatExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
