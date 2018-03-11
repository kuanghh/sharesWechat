package com.khh.web.controller;

import com.khh.base.bean.ResponseBean;
import com.khh.base.controller.BaseController;
import com.khh.web.service._interface.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 用户controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean register() throws Exception{
        ResponseBean responseBean = new ResponseBean();


        return null;
    }


}
