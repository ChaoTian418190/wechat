package com.wechat.model.req;

/**
 * 语音消息 
 */
public class VoiceMessage extends BaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8050667853840808892L;
	/** 
     * 媒体ID 
     */  
    private String MediaId;  
    /** 
     * 语音格式 
     */  
    private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	} 
}
