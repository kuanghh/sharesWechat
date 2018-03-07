package com.khh.wechat.vo.message.response;


import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;

public class VideoMessage extends BaseMessage {

	private Video Video;

	public VideoMessage(){}

	public VideoMessage(BaseRequestMessage message) {
		super(message);
		this.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
	}

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
	

}
