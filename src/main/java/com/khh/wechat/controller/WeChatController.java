package com.khh.wechat.controller;

import com.khh.base.controller.BaseController;
import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.util.WeiXinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 公众号核心处理类
 */
@Controller
@RequestMapping
public class WeChatController extends BaseController{

    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(String signature, String timestamp, String nonce, String echostr) throws Exception {
        System.out.println("进来了");
        if (WeiXinUtil.checkSignature(signature, timestamp, nonce)) {



            return echostr;        // 校验通过，原样返回echostr参数内容
        }
        return null;

    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    @ResponseBody
    public String doPost(HttpServletRequest request) throws Exception {

        System.out.println("进来了哦");

        Map<String, Object> paramMap = MessageUtil.parseXml2(request);

        // todo  初始化菜单， 到时候，在新人第一次关注的时候开始创建菜单
        WeiXinUtil.initMenu();

        return null;
    }
}
