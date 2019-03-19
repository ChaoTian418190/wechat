package wechat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import com.wechat.util.HttpUtil;

public class TestSet {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
/*		Set set = new HashSet<>();
		set.add("A");
		set.add("A");
		System.out.println(set.size());*/
		
//		String result = HttpUtil.sendGetData("http://baidu.com", "utf-8");
		String token = "8_9RKdT8GmDUxe1R6IiSfA7kA6aAm0BVX1dy2UYa1EqwQeOFx0cCPadrilDIAsqmRFuicCvSnLyVuh1SjWQCZ8iYMuj-1CENMDrZOw5g4ssJfycWlu1HMWeBf4K8cPMlZvnQVb75_Cq8d3WCk7CSScAGAKAA";
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
				   	     	+"\"type\":\"miniprogram\","
				   	     	+ "\"name\":\"跳转小程序\","
				   	     	+ "\"url\":\"http://mp.weixin.qq.com\","
				   	     	+ "\"appid\":\"wx286b93c14bbf93aa\","
				   	     	+ "\"pagepath\":\"pages/lunar/index\""
				   	     + "},"
				   	     + "{"
				   	     	+"\"type\":\"click\","
				   	     	+ "\"name\":\"赞一下我们\","
				   	     	+ "\"key\":\"V1001_GOOD\""
				   	     + "}]"
				   	+ "}]"
			+ "}";
//		System.out.println("---------------------------");
		System.out.println(menu);
//		System.out.println("---------------------------");
	}
}
