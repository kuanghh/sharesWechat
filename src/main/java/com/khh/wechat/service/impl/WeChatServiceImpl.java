package com.khh.wechat.service.impl;

import com.khh.base.common.Const;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.UserMapper;
import com.khh.web.domain.User;
import com.khh.wechat.exception.WechatExceptionEnum;
import com.khh.wechat.service.WeChatService;
import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.util.WeiXinUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;
import com.khh.wechat.vo.message.response.BaseMessage;
import com.khh.wechat.vo.message.response.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 微信公众号核心处理类
 */
@Service("wechatService")
public class WeChatServiceImpl implements WeChatService {

    private static Logger log = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public String processReq(BaseRequestMessage message) throws Exception {

        String msgType = message.getMsgType();

        String sendMessage = "";

        //todo 到时候使用redis 将 msgID缓存起来，方便去重

        if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) { //文本消息
            sendMessage = handleTextTypeMessage(message);

        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {// 图片消息
            //不作处理
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {// 地理位置信息
            //不作处理
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {// 链接消息
            //不作处理
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {// 音频信息
            //不作处理
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {// 视频信息
            //不作处理
        } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件消息
            sendMessage = handleEventTypeMessage(message);
        }


        return sendMessage;
    }

    /**
     * 处理文本消息
     * @param message
     * @return
     */
    private String handleTextTypeMessage(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);
        textMessage.setContent("闭嘴!!!!!");

        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 处理事件推送类型的消息,
     * @param message
     * return 返回消息字符串
     */
    private String handleEventTypeMessage(BaseRequestMessage message) throws Exception{

        String event = (String) message.getKey("Event");

        if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(event)){//关注事件

            // todo  初始化菜单， 到时候，在新人第一次关注的时候开始创建菜单
            WeiXinUtil.initMenu();
            event = handleSubscribeTypeMessage(message);

        }else if(MessageUtil.EVENT_TYPE_CLICK.equals(event)){// 如果是按钮点击事件

            event = handleClickTypeMessage(message);

        }

        return event;
    }

    /**
     * 处理第一次关注当前公众号事件
     * @param message
     * @return
     * @throws Exception
     */
    public String handleSubscribeTypeMessage(BaseRequestMessage message)throws Exception{

        //先获取用户OpenId
        String openId = message.getFromUserName();
        String text = null;

        User user = userMapper.findByOpenId(openId);
        if(user == null || user.getIsBinding() == 0){//还没有用户记录，或者用户还没注册的话
            text = "欢迎用户使用公众号，请点击菜单\"我\"--->\"绑定注册\",进行注册哦!";

        }else if(user.getIsBinding() == 1){ // 用户已经注册了
            text = "亲爱的用户在 " + DateUtil.dateToString(user.getRegisterTime()) + " 注册成功";
        }

        TextMessage textMessage = new TextMessage(message);
        textMessage.setContent(text);
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 处理按钮点击事件
     * @param message
     * @return
     * @throws Exception
     */
    private String handleClickTypeMessage(BaseRequestMessage message) throws Exception{

        BaseMessage baseMessage = null;

        //获取按钮key值
        String button_key = (String) message.getKey("EventKey");

        if(button_key == null){
            throw new RuntimeException(WechatExceptionEnum.button_key_null.getDesc());
        }

        if(WeiXinUtil.button_a3_1_key.equals(button_key)){//绑定注册

            baseMessage = handleRegisterEvent(message);

        }else if(WeiXinUtil.button_a3_2_key.equals(button_key)){// 我的信息


        }
        return MessageUtil.baseMessageToXml(baseMessage);
    }

    /**
     * 处理绑定注册信息
     * @param message
     * @return
     */
    private BaseMessage handleRegisterEvent(BaseRequestMessage message){
        TextMessage textMessage = new TextMessage(message);

        // todo  这里需要，如果用户已经注册了，则提示用户，“亲爱的用户你已经在xxxx时间注册了，谢谢”

        //获取用户的openId
        String openId = message.getFromUserName();

        String url = Const.project_loc_localhost + "/html/register.html?openId=" + openId;

        //组织url
        String text = ("亲爱的用户，欢迎点击<a href='" + url + "'>注册</a>进入注册页面，并进行注册");

        textMessage.setContent(text);
        return textMessage;
    }
}
