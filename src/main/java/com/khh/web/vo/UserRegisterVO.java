package com.khh.web.vo;

import com.khh.web.domain.User;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 用户在页面上传的注册信息
 */
public class UserRegisterVO implements Serializable{

    private String id;
    @NotEmpty
    private String account;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String openId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenId() {
        return openId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    //    public User voToPO(){
//        User user = new User();
//        if(this.account != null){
//            user.setAccount(this.account);
//        }
//
//        if(this.name != null){
//            user.setName(this.name);
//        }
//
//        if(this.email != null){
//            user.setEmail(this.email);
//        }
//
//        if(this.phone != null){
//            user.setPhone(this.phone);
//        }
//
//
//        return null;
//    }
}
