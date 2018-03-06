package com.khh.wechat.vo.message.request;

/*
 * 文本消息
 */

public class TextMessage extends BaseMessage {
	//消息内容
	private String content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
