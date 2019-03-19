package com.wechat.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ValidToken {
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> test(){
		Map<String,Object> map = new HashMap<>();
		map.put("name", "zhangsan");
		return map;
	}     
}
