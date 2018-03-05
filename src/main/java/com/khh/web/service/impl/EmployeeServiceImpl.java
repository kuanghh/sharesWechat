package com.khh.web.service.impl;

import com.khh.web.dao.EmployeeMapper;
import com.khh.web.domain.Employee;
import com.khh.web.service._interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/7/25.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee findById(int id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }
}
