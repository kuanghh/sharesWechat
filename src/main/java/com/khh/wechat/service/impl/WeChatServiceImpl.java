package com.khh.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.khh.base.common.Const;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.UserMapper;
import com.khh.web.domain.TbShares;
import com.khh.web.domain.User;
import com.khh.web.enm.SharesParamEnum;
import com.khh.web.service._interface.SharesService;
import com.khh.web.service._interface.UserService;
import com.khh.web.util.SharesUtil;
import com.khh.web.util.UserUtil;
import com.khh.web.vo.CallSharesVO;
import com.khh.web.vo.SharesVO;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 微信公众号核心处理类
 */
@Service("wechatService")
public class WeChatServiceImpl implements WeChatService {

    private static Logger log = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sharesService")
    private SharesService sharesService;

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


        String content = message.getKey("Content").toString(); //用户发的消息
        String responseContent = "闭嘴!!!!!";

        if(content.indexOf("#") == 0){
            responseContent = callApppointSharesMethod(message, content.substring(1,content.length()));
        }
        textMessage.setContent(responseContent);
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 调用实时爬虫抓取数据方法
     * @param message
     * @return
     */
    private String callApppointSharesMethod(BaseRequestMessage message, String sharesNum) throws Exception{

        String response = "";

        //先从数据库中,查找是否存在这只股票
        TbShares shares = sharesService.findBySharesNum(sharesNum);
        String openId = message.getFromUserName();
        if(shares == null){
            response = "你所输入的证券代码不存在,本系统只支持深证A股的数据";
        }else{
            //在这里组装调用爬虫的json数据
            CallSharesVO vo = new CallSharesVO();
            vo.setOpen_id(openId);
            vo.setUrl(shares.getSharesHref());
            vo.setMsg_id(message.getMsgId());
            vo.setShares_num(shares.getSharesNum());

            response = SharesUtil.runAppointSpider(vo, shares.getSharesName());
            if(response == null || response.trim().length() == 0){
                response = "系统繁忙!!..";
            }

        }
        return response;
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

        User user = userService.findByOpenId(openId);
        if(user == null || user.getIsBinding() == UserUtil.USER_BINGDING_UNREGISTER){//还没有用户记录，或者用户还没注册的话
            text = "欢迎用户使用公众号，请点击菜单\"我\"--->\"绑定注册\",进行注册哦!";

            //对用户信息进行插入
            user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setOpenId(openId);
            user.setCreateTime(new Date());
            user.setIsBinding(UserUtil.USER_BINGDING_UNREGISTER);
            user.setStatus(UserUtil.USER_STATE_UNSUBSCRIBE);

            userService.insertPO(user,false);

        }else if(user.getIsBinding().equals(UserUtil.USER_BINGDING_REGISTER)){ // 用户已经注册了

            if(user.getStatus().equals(UserUtil.USER_STATE_UNSUBSCRIBE)){ //如果用户注册了,但是是返回关注的则
                user.setStatus(UserUtil.USER_BINGDING_REGISTER);
                userService.updatePO(user, false);
            }
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
            //todo ...

        }else if(WeiXinUtil.button_a1_1_key.equals(button_key)){// 实时股票信息
            baseMessage = handleRealTimeSharesEvent(message);

        }else if(WeiXinUtil.button_a2_1_key.equals(button_key)){//成交量top5
            baseMessage = handleVolumeTop5Event(message);

        }else if(WeiXinUtil.button_a2_2_key.equals(button_key)){//最高价top5
            baseMessage = handleHighPriceTop5Event(message);

        }else if(WeiXinUtil.button_a2_3_key.equals(button_key)){//涨幅top5
            baseMessage = handleRangeTop5Event(message);
        }
        return MessageUtil.baseMessageToXml(baseMessage);
    }

    /**
     * 处理获取昨日成交量top5的数据
     * @param message
     * @return
     */
    private BaseMessage handleVolumeTop5Event(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);

        List<SharesVO> shareVOList = sharesService.findTop5SharesByField(SharesParamEnum.volume.getField());
        String topString = SharesUtil.getTopString(false, shareVOList, SharesParamEnum.volume);
        textMessage.setContent(topString);

        return textMessage;
    }

    /**
     * 处理获取昨日最高价top5的数据
     * @param message
     * @return
     */
    private BaseMessage handleHighPriceTop5Event(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);

        List<SharesVO> shareVOList = sharesService.findTop5SharesByField(SharesParamEnum.ceilling_price.getField());
        String topString = SharesUtil.getTopString(false, shareVOList, SharesParamEnum.ceilling_price);
        textMessage.setContent(topString);

        return textMessage;
    }

    /**
     * 处理获取昨日最高价top5的数据
     * @param message
     * @return
     */
    private BaseMessage handleRangeTop5Event(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);

        List<SharesVO> shareVOList = sharesService.findTop5SharesByField(SharesParamEnum.rise_and_fall_range.getField());
        String topString = SharesUtil.getTopString(false, shareVOList, SharesParamEnum.rise_and_fall_range);
        textMessage.setContent(topString);

        return textMessage;
    }

    /**
     * 处理实时股票信息按钮事件
     * @param message
     * @return
     */
    private BaseMessage handleRealTimeSharesEvent(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);

        String text = "请以<a href='javascript:void(0)'>'#+股票代码'</a>输入代码获取实时数据信息(例如:#603899)";
        textMessage.setContent(text);

        return textMessage;
    }

    /**
     * 处理绑定注册信息
     * @param message
     * @return
     */
    private BaseMessage handleRegisterEvent(BaseRequestMessage message) throws Exception{
        TextMessage textMessage = new TextMessage(message);

        //获取用户的openId
        String openId = message.getFromUserName();
        String text = "";

        User user = userService.findByOpenId(openId);//如果用户已经注册了，则提示问候语

        if(user != null && user.getIsBinding().equals(UserUtil.USER_BINGDING_REGISTER) && user.getStatus().equals(UserUtil.USER_STATE_SUBSCRIBE)){

            text = "亲爱的用户你已经在" + DateUtil.dateToString(user.getRegisterTime()) + "时间注册了，谢谢你的关注~";
        }else {

            String url = Const.project_loc_localhost + "/html/register.html?openId=" + openId;
            //组织url
            text = ("亲爱的用户，欢迎点击<a href='" + url + "'>注册</a>进入注册页面，并进行注册");
        }
        textMessage.setContent(text);
        return textMessage;
    }
}
