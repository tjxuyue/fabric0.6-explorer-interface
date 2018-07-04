package com.project.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.entity.Demo;
import com.project.entity.ResponseBody;
import com.project.service.DemoService;
import com.project.service.RestfulService;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;
	@Autowired
	private RestfulService restfulService;

	@Value("${ip.vp2}")
	private String ip2;
	@Value("${port.vp2}")
	private String port2;

	private String SUCCESS = "success";
	private String ERROR = "error";

	static private String URL_PRE_HTTP = "http://";
	static private String URL_PRE_HTTPS = "https://";
	static private String url1 = "/chain";

	@RequestMapping(value = "test")
	public String test() {
		Demo demo = new Demo();
		demo.setId(1);
		demo.setName("test");
		demoService.demo(demo);
		return "测试成功";
	}

	@RequestMapping(value = "chain")
	public String chain() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("height", 231);
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = "getBlockNum")
	public ResponseBody getBlockNum() {

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip2);
		url.append(":");
		url.append(port2);
		url.append(url1);
		System.out.println(url);
		String result = restfulService.get(url.toString(), new HashMap<String, Object>());
		JSONObject json = JSON.parseObject(result);
		if (json.containsKey("height")) {
			responseBody.setData(json.get("height").toString());
			responseBody.setStatus(SUCCESS);
			return responseBody;
		}
		responseBody.setStatus(ERROR);
		return responseBody;
	}
}
