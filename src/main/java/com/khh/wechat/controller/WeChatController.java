package com.khh.wechat.controller;

import com.khh.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 */
@Controller
@RequestMapping(value = "/wechat")
public class WeChatController extends BaseController{

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        return null;

    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {

        return null;

    }
}
