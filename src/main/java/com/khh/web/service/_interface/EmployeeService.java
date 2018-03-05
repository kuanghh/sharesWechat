package com.khh.web.service._interface;

import com.khh.web.domain.Employee;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/7/25.
 */
public interface EmployeeService {

    Employee findById(int id);

    List<Employee> findAll();
}
