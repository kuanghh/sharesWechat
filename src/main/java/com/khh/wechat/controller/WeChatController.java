package com.khh.wechat.controller;

import com.khh.base.controller.BaseController;
import com.khh.wechat.service.WeChatService;
import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.util.WeiXinUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 公众号核心处理类
 */
@Controller
@RequestMapping
public class WeChatController extends BaseController{

    @Resource(name = "wechatService")
    private WeChatService wechatService;

    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(String signature, String timestamp, String nonce, String echostr) throws Exception {
        System.out.println("进来了");

        if (WeiXinUtil.checkSignature(signature, timestamp, nonce)) {

            return echostr;        // 校验通过，原样返回echostr参数内容
        }
        return null;
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST, produces = "text/html;charset=utf-8")//只有配置了produces = "text/html;charset=utf-8"，才不会在公众号里面显示中文的乱码
    @ResponseBody
    public String doPost(HttpServletRequest request) throws Exception {

        BaseRequestMessage baseRequestMessage = MessageUtil.parseRequest(request);

        String sendMessage = wechatService.processReq(baseRequestMessage);

        return sendMessage;
    }
}
