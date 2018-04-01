package com.khh.wechat.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/4/1.
 */
public class TextVO implements Serializable{

    private String content;

    public TextVO(){

    }

    public TextVO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
