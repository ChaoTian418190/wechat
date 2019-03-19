package com.wechat.model.resp;

/** 
 * 文本消息 
 */ 
public class TextMessage extends BaseMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287810190284730678L;
	/** 
     * 回复的消息内容 
     */  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
