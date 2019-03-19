package com.wechat.model.req;

/** 
 * 文本消息 
 */
public class TextMessage extends BaseMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 779097108848190032L;
	/**
	 * 文本消息内容
	 */
	private String Content;  
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
