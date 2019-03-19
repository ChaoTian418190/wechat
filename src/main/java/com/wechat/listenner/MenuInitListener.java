package com.wechat.listenner;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wechat.model.Account;
import com.wechat.service.AccountService;
import com.wechat.util.HttpUtil;
import com.wechat.util.JsonUtil;

@Component
public class MenuInitListener implements CommandLineRunner{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public void run(String... arg0) throws Exception {
		String url = "";
		Account account = accountService.getAccountInfo();
		String access_token = account.getAccessToken();
		//如果accesstoken为空或者超过两小时,则重新获取accesstoken,并更新数据库
		if(account.getAccessToken() == null || account.getAccessToken().equals("") 
				|| (System.currentTimeMillis() - account.getUpdateTime().getTime()) > 7200000 ){
			//获取access_token的url
			url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+account.getAppid()+"&secret="+account.getAppsecret();
			String result = HttpUtil.sendGetData(url,"utf-8");
			Map<String,Object> map = JsonUtil.readValueToMap(result);
			access_token = (String) map.get("access_token");
			account.setAccessToken(access_token);
			accountService.updateAccountById(account);
		}
		//创建菜单的url
		url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		String menu = "{"
				+ "\"button\":["
				  + "{\"name\":\"今日歌曲\","
				     + "\"sub_button\":["
				       + "{"
				         + "\"type\":\"click\","
				         + "\"name\":\"流行音乐\","
				         + "\"key\":\"MUSIC_01\""
				         + "},"
				         + "{"
				         + "\"type\":\"click\","
				         + "\"name\":\"网络歌曲\","
				         + "\"key\":\"MUSIC_02\""
				         + "},"
				         + "{"
				         + "\"type\":\"click\","
				         + "\"name\":\"流金岁月\","
				         + "\"key\":\"MUSIC_03\""
				         + "},"
				         + "{"
				         + "\"type\":\"click\","
				         + "\"name\":\"经典怀旧\","
				         + "\"key\":\"MUSIC_04\""
				         + "}"
				       + "]"
				   + "},"
				   + "{"
				       + "\"name\":\"生活助手\","
				       + "\"sub_button\":["
				          + "{"
				          + "\"type\":\"click\","
				          + "\"name\":\"天气预报\","
				          + "\"key\":\"LIVE_01\""
				          + "},"
				          + "{"
				          + "\"type\":\"click\","
				          + "\"name\":\"公交查询\","
				          + "\"key\":\"LIVE_02\"}"
				        + "]"
				   + "},"
				   + "{"
				   	    + "\"name\":\"菜单\","
				   	    + "\"sub_button\":["
				   	    + "{"
				   	    	+"\"type\":\"view\","
				   	    	+ "\"name\":\"soso搜索\","
				   	    	+ "\"url\":\"http://www.soso.com/\""
				   	     + "},"
				   	     + "{"
				   	     	+"\"type\":\"view\","
				   	     	+ "\"name\":\"百度搜索\","
				   	     	+ "\"url\":\"http://www.baidu.com/\""
				   	     + "},"
				   	     + "{"
				   	     	+"\"type\":\"view\","
				   	     	+ "\"name\":\"跳转小程序\","
				   	     	+ "\"url\":\"http://mp.weixin.qq.com\""
				   	     + "},"
				   	     + "{"
				   	     	+"\"type\":\"click\","
				   	     	+ "\"name\":\"赞一下我们\","
				   	     	+ "\"key\":\"V1001_GOOD\""
				   	     + "}]"
				   	+ "}]"
			+ "}";
		String result = HttpUtil.sendPostDataByJson(url, menu, "utf-8");
		System.out.println("启动的时候就执行的方法..........."+result);
	}
}
