package com.khh.wechat.vo.message.response;

/*
 * 图文model
 */

public class Article {
	//图文信息名称
	private String Title;
	//图文消息描述
	private String Descripton;
	//图片链接,支持JPG,PNG格式,较好的效果为大图640*320，
	//小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的
	//url一致
	private String PicUrl;
	//点击图文消息跳转链接
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescripton() {
		return Descripton;
	}
	public void setDescripton(String descripton) {
		Descripton = descripton;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	

}
