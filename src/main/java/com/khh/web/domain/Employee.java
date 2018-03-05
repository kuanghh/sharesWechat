package com.khh.web.domain;

import java.io.Serializable;

public class Employee implements Serializable{

    private Integer id;//唯一标识

    private String name;//名字

    private Long salary;//薪水，保存到分

    private Integer deptId; //部门Id


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }


}