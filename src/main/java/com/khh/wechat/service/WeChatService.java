package com.khh.wechat.service;

import com.khh.wechat.vo.message.request.BaseRequestMessage;

/**
 * Created by 951087952@qq.com on 2018/3/5.
 * 公众号核心业务接口
 */
public interface WeChatService {

    String processReq(BaseRequestMessage message) throws Exception;
}
