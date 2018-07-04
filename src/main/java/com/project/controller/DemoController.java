package com.project.controller;

import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
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
	static private String url2 = "/network/peers";
	static private String url3 = "/chain/blocks";
	static private String url4 = "/transactions";

	/*
	 * @RequestMapping(value = "test") public String test() { Demo demo = new
	 * Demo(); demo.setId(1); demo.setName("test"); demoService.demo(demo); return
	 * "测试成功"; }
	 * 
	 * @RequestMapping(value = "chain") public String chain() { HashMap<String,
	 * Object> map = new HashMap<String, Object>(); map.put("height", 231); return
	 * JSON.toJSONString(map); }
	 */
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
			responseBody.setData(json.toString());
			responseBody.setStatus(SUCCESS);
			return responseBody;
		}
		responseBody.setStatus(ERROR);
		return responseBody;
	}

	@RequestMapping(value = "getPeerList")
	public ResponseBody getPeerList() {

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip2);
		url.append(":");
		url.append(port2);
		url.append(url2);
		System.out.println(url);
		String result = restfulService.get(url.toString(), new HashMap<String, Object>());
		JSONObject json = JSON.parseObject(result);
		if (json.containsKey("peers")) {
			responseBody.setData(json.getString("peers"));
			responseBody.setStatus(SUCCESS);
			return responseBody;
		}
		responseBody.setStatus(ERROR);
		return responseBody;
	}

	@RequestMapping(value = "getBlock/{num}")
	public ResponseBody getBlockInfor(@PathVariable("num") String num) {

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip2);
		url.append(":");
		url.append(port2);
		url.append(url3);
		url.append("/");
		url.append(num);
		System.out.println(url);
		String result = restfulService.get(url.toString(), new HashMap<String, Object>());
		JSONObject json = JSON.parseObject(result);
		if (!json.containsKey("Error")) {
			responseBody.setData(json.toJSONString());
			responseBody.setStatus(SUCCESS);
			return responseBody;
		}
		responseBody.setStatus(ERROR);
		return responseBody;
	}

	@RequestMapping(value = "getTransaction/{txid}")
	public ResponseBody getTransaction(@PathVariable("txid") String txid) {

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip2);
		url.append(":");
		url.append(port2);
		url.append(url4);
		url.append("/");
		url.append(txid);
		System.out.println(url);
		String result = restfulService.get(url.toString(), new HashMap<String, Object>());
		JSONObject json = JSON.parseObject(result);
		if (!json.containsKey("Error")) {
			responseBody.setData(json.toJSONString());
			responseBody.setStatus(SUCCESS);
			return responseBody;
		}
		responseBody.setStatus(ERROR);
		return responseBody;
	}

}
