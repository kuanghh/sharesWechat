package com.khh.wechat.service.impl;

import com.khh.wechat.service.WeChatService;
import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.util.WeiXinUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;
import com.khh.wechat.vo.message.response.TextMessage;
import org.springframework.stereotype.Service;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 微信公众号核心处理类
 */
@Service("wechatService")
public class WeChatServiceImpl implements WeChatService {

    public String processReq(BaseRequestMessage message) throws Exception {

        String msgType = message.getMsgType();

        String sendMessage = "";

        //到时候使用redis 将 msgID缓存起来，方便去重

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

        String event = (String) message.getKey("event");

        if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(event)){//关注事件
            // todo  初始化菜单， 到时候，在新人第一次关注的时候开始创建菜单
            WeiXinUtil.initMenu();
        }

        return null;
    }
}
