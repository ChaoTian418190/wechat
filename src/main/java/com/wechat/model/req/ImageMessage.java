package com.wechat.model.req;
/**
 * 图片消息
 */
public class ImageMessage extends BaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8646185903216364587L;
	/**
	 * 图片url
	 */
	private String picUrl;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
