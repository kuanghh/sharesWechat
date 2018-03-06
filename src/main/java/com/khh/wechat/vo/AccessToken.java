package com.khh.wechat.vo;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/3/6.
 */
public class AccessToken implements Serializable{

    private String token;

    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
