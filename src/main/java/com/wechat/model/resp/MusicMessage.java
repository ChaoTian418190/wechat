package com.wechat.model.resp;

/** 
 * 音乐消息 
 */ 
public class MusicMessage extends BaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2291339740184949017L;
	/** 
     * 音乐 
     */  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}
