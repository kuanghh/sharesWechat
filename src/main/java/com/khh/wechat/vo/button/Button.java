package com.khh.wechat.vo.button;


/*
 * 按钮
 */

public class Button {

	public static String BUTTON_TYPE_CLICK = "click";		 //点击类型
	public static String BUTTON_TYPE_VIEW = "view";		 //网页类型
	public static String BUTTON_TYPE_MINIPROGRAM = "miniprogram"; //小程序

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
