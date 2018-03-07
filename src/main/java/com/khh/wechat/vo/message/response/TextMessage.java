package com.khh.wechat.vo.message.response;


import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;

/*
 * 文本消息
 * 
 */
public class TextMessage extends BaseMessage {

	//回复的消息内容
	private String Content;

	public TextMessage(){}

	public TextMessage(BaseRequestMessage message) {
		super(message);
		this.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}
	
}
