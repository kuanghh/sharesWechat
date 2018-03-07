package com.khh.wechat.vo.message.response;


import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.vo.message.request.BaseRequestMessage;

/*
 * 音乐消息
 */
public class MusicMessage extends BaseMessage {

	//音乐
	private Music Music;

	public MusicMessage(){

	}

	public MusicMessage(BaseRequestMessage message) {
		super(message);
		this.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
	}

	/**
	 * @return the music
	 */
	public Music getMusic() {
		return Music;
	}

	/**
	 * @param music the music to set
	 */
	public void setMusic(Music music) {
		Music = music;
	}
	
	
}
