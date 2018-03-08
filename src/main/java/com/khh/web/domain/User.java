package com.khh.web.domain;

import java.util.Date;

public class User {
    private String id;

    private String openId;

    private String name;

    private String phone;

    private String email;

    private String account;

    private Byte isBinding;

    private Byte status;

    private Date createTime;

    private Date registerTime;

    private Date unregisterTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Byte getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Byte isBinding) {
        this.isBinding = isBinding;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUnregisterTime() {
        return unregisterTime;
    }

    public void setUnregisterTime(Date unregisterTime) {
        this.unregisterTime = unregisterTime;
    }
}