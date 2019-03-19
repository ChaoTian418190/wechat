package com.wechat.token;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.wechat.model.resp.Article;
import com.wechat.util.CheckoutUtil;
import com.wechat.util.MessageUtil;
import com.wechat.util.XmlUtil;

public class ValidTokenServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3500665920798714626L;
	private Logger logger = Logger.getLogger(ValidTokenServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
		boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    logger.info(e);
                }
            }
         }
	  }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException{
			req.setCharacterEncoding("UTF-8");  
		    resp.setCharacterEncoding("UTF-8");  
		    PrintWriter out = resp.getWriter();
		    String message = "";
		    String respContent = "";  
		    
		    try {  
		        //把微信返回的xml信息转义成map  
		        Map<String, String> map = XmlUtil.xmlToMap(req);  
		        
		        //发送方帐号(open_id),消息来源用户标识
		        String fromUserName = map.get("FromUserName");
		        
		        //公众帐号,消息目的用户标识
		        String toUserName = map.get("ToUserName");
		        
		        ////消息类型 
		        String msgType = map.get("MsgType"); 
		        
		        //文本消息
		        if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
		        	// 接收用户发送的文本消息内容  
	                String content = map.get("Content");
	                
	                List<Article> articleList = new ArrayList<>();
	                //回复文本消息
		        	if(content.equals("1")){
		        		respContent = "亲，我这里只是用来测试给您回复文本消息的,\n要获取图文信息请回复2或3哟!么么哒\ue106\ue106";
		        		message = MessageUtil.initText(toUserName, fromUserName, respContent);
		        		
		        		//单图文消息
		        	}else if(content.equals("2")){
		        		Article article = new Article();  
	                    article.setTitle("我是一条单图文消息");  
	                    article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");  
	                    article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");  
	                    article.setUrl("http://tuposky.iteye.com");  
	                    articleList.add(article); 
	                    message = MessageUtil.initNewMessage(toUserName, fromUserName, articleList);
		        	//多图文消息
		        	}else if(content.equals("3")){
		        		Article article1 = new Article();  
	                    article1.setTitle("我是一条多图文消息");  
	                    article1.setDescription("");  
	                    article1.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20130913/2013091314543416032.jpg");  
	                    article1.setUrl("http://tuposky.iteye.com/blog/2008583");  
	  
	                    Article article2 = new Article();  
	                    article2.setTitle("微信公众平台开发教程Java版（二）接口配置 ");  
	                    article2.setDescription("");  
	                    article2.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20131021/2013102111243367254.jpg");  
	                    article2.setUrl("http://tuposky.iteye.com/blog/2008655");  
	  
	                    Article article3 = new Article();  
	                    article3.setTitle("微信公众平台开发教程Java版(三) 消息接收和发送");  
	                    article3.setDescription("");  
	                    article3.setPicUrl("http://www.isic.cn/viewResourcesAction//logo/20131021/2013102111291287031.jpg");  
	                    article3.setUrl("http://tuposky.iteye.com/blog/2017429");  
	  
	                    articleList.add(article1);  
	                    articleList.add(article2);  
	                    articleList.add(article3); 
	                    message = MessageUtil.initNewMessage(toUserName, fromUserName, articleList);
		        	}
		        }else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){  
		        	// 事件类型  
	                String eventType = map.get("Event");
	                if(MessageUtil.EVENT_SUB.equals(eventType)){
	                	// 关注  
	                    respContent = "亲,您终于来了,等您好久了,这里为您提供如下服务！\n";  
	                    StringBuffer contentMsg = new StringBuffer();  
	                    contentMsg.append("您还可以回复下列数字，体验相应服务").append("\n\n");  
	                    contentMsg.append("1  测试文本消息").append("\n");  
	                    contentMsg.append("2  测试单图文消息").append("\n");  
	                    contentMsg.append("3  测试多图文消息").append("\n");  
	                    respContent = respContent+contentMsg.toString();
	                    message = MessageUtil.initText(toUserName, fromUserName, respContent);
	                }else if(MessageUtil.EVENT_UNSUB.equals(eventType)){
	                	// 取消关注,用户接受不到我们发送的消息了，可以在这里记录用户取消关注的日志信息
	                }else if(MessageUtil.EVENT_CLICK.equals(eventType)){
	                	//点击事件
	                }
		        }
		    } catch (DocumentException e) {  
		    	logger.info(e);  
		    }finally{  
		        out.println(message);  
		        if(out!=null){  
		            out.close();  
		        }
		    }
	    }	    
  }
