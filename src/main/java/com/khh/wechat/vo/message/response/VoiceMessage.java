package com.khh.wechat.vo.message.response;


import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;

public class VoiceMessage extends BaseMessage{
	
	private Voice Voice;

	public VoiceMessage(){}

	public VoiceMessage(BaseRequestMessage message) {
		super(message);
		this.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);

	}

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
	

}
