package com.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wechat.model.resp.Article;
import com.wechat.model.resp.MusicMessage;
import com.wechat.model.resp.NewsMessage;
import com.wechat.model.resp.TextMessage;

public class XmlUtil {
	/* 
     * xml转map 
     */  
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{  
        HashMap<String, String> map = new HashMap<String,String>();  
        SAXReader reader = new SAXReader();  
  
        InputStream ins = request.getInputStream();  
        Document doc = reader.read(ins);  
  
        Element root = doc.getRootElement();  
        @SuppressWarnings("unchecked")  
        List<Element> list = (List<Element>)root.elements();  
  
        for(Element e:list){  
            map.put(e.getName(), e.getText());  
        }  
        ins.close();  
        return map;  
    }  
    /* 
     * 文本消息对象转xml,
     * 相应的文本消息,即推送到微信服务器的消息(resp中)
     */  
    public static String textMsgToxml(TextMessage textMessage){  
        XStream xstream = new XStream();  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }  
    
    /** 
     * 音乐消息对象转换成xml 
     *  
     * @param musicMessage 音乐消息对象 
     * @return xml 
     */  
    public static String musicMessageToXml(MusicMessage musicMessage) {
    	XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }  
    
    /** 
     * 图文消息对象转换成xml 
     *  
     * @param newsMessage 图文消息对象 
     * @return xml 
     */  
    public static String newsMessageToXml(NewsMessage newsMessage) {
    	XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(newsMessage);  
    }  
}
