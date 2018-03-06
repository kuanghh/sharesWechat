package com.khh.wechat.vo.message.request;


/*
 * 图片信息
 */

public class ImageMessage extends BaseMessage {
	//图片链接
	private String picUrl;

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
