package com.khh.base.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * BaseController  用来处理异常
 */
public class BaseController {
    /**
     * 使用@ExceptionHandler注解，继承此类的Controller发生异常时会自动执行该方法
     * @param e
     * @return
     */
    @ExceptionHandler
    public String exception(Exception e) {

        //对异常进行判断做相应的处理

        return "forward:/error.jsp";
    }
}
