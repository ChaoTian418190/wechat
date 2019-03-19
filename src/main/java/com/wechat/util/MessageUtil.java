package com.wechat.util;

import java.util.Date;
import java.util.List;

import com.wechat.model.resp.Article;
import com.wechat.model.resp.NewsMessage;
import com.wechat.model.resp.TextMessage;


public class MessageUtil {
	/**
	 * 文本消息
	 */
	public static final String MESSAGE_TEXT = "text";
	/**
	 * 图片消息
	 */
    public static final String MESSAGE_IMAGE = "image";
    /**
     * 语言消息
     */
    public static final String MESSAGE_VOICE = "voice";
    /**
     * 视频消息
     */
    public static final String MESSAGE_VIDEO = "video";
    /**
     * 链接消息
     */
    public static final String MESSAGE_LINK = "link";
    /**
     * 位置消息
     */
    public static final String MESSAGE_LOCATION = "location";
    /**
     * 图文消息
     */
    public static final String MESSAGE_NEWS = "news";
    /**
     * 事件
     */
    public static final String MESSAGE_EVENT = "event";
    /**
     * 关注
     */
    public static final String EVENT_SUB = "subscribe";
    /**
     * 取消关注
     */
    public static final String EVENT_UNSUB = "unsubscribe";
    /**
     * 点击事件
     */
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";
    
    public static String initText(String toUserName, String fromUserName, String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setFuncFlag(0);
        text.setContent(content);
        return XmlUtil.textMsgToxml(text);
    }
    
    public static String initNewMessage(String toUserName, String fromUserName,List<Article> articleList){
    	NewsMessage newsMessage = new NewsMessage();
    	newsMessage.setFromUserName(toUserName);
    	newsMessage.setToUserName(fromUserName);
    	newsMessage.setMsgType(MESSAGE_NEWS);
    	newsMessage.setCreateTime(new Date().getTime());
    	newsMessage.setFuncFlag(0);
    	newsMessage.setArticleCount(articleList.size());
    	newsMessage.setArticles(articleList);
    	return XmlUtil.newsMessageToXml(newsMessage);
    }
}
