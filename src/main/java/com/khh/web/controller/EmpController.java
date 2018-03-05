package com.khh.web.controller;

import com.khh.web.domain.Employee;
import com.khh.web.service._interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/7/25.
 *
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmployeeService employeeService;


    @ResponseBody
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Employee findById(int id) throws Exception{

        return employeeService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Employee> findAll() throws Exception{

        return employeeService.findAll();
    }

}
