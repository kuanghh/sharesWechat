package com.khh.wechat.vo.message.request;


import java.util.Map;

//消息基类（普通用户 -> 公众帐号）
public class BaseRequestMessage {


	// 开发者微信号
	private String toUserName;
	
	// 发送方帐号（一个 OpenID）
	private String fromUserName;
	
	// 消息创建时间 （整型）
	private String createTime;
	
	// 消息类型（text/image/location/link）
	private String msgType;
	
	// 消息 id，64 位整型
	private String msgId;

	private Map<String,Object> paramMap;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Object getKey(String key){
		if(this.paramMap != null){
			return this.paramMap.get(key);
		}
		return null;
	}
}
