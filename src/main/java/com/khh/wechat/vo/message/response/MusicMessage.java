package com.khh.wechat.vo.message.response;


/*
 * 音乐消息
 */
public class MusicMessage extends BaseMessage {

	//音乐
	private Music Music;

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
