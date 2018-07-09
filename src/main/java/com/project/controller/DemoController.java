package com.project.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.entity.ChaincodeRequest;
import com.project.entity.ChaincodeRequest.Params;
import com.project.entity.ChaincodeRequest.ChaincodeID;
import com.project.entity.ChaincodeRequest.CtorMsg;
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
	@Value("${ip.vp1}")
	private String ip1;
	@Value("${port.vp1}")
	private String port1;

	private String SUCCESS = "success";
	private String ERROR = "error";

	static private String URL_PRE_HTTP = "http://";
	static private String URL_PRE_HTTPS = "https://";
	static private String url1 = "/chain";
	static private String url2 = "/network/peers";
	static private String url3 = "/chain/blocks";
	static private String url4 = "/transactions";
	static private String url5 = "/chaincode";

	@RequestMapping(value = "test")
	public String test() {
		ChaincodeRequest chaincode = new ChaincodeRequest();
		chaincode.setId(1);
		chaincode.setJsonrpc("2.0");
		chaincode.setMethod("query");

		Params params = chaincode.new Params();
		ChaincodeRequest.ChaincodeID chaincodeId = chaincode.new ChaincodeID();
		ChaincodeRequest.CtorMsg ctorMsg = chaincode.new CtorMsg();
		chaincodeId.setName(
				"7a87c41d0c804182ef09c39e2d648984f5b6cda0d1b184764b86af2497fbf7142dd5dce59392ba731578b015352b3df6939912176072b233867b7dd7f5ea0864");
		ctorMsg.setArgs(new String[] { "1" });
		ctorMsg.setFunction("query");
		params.setChaincodeID(chaincodeId);
		params.setType(1);
		params.setCtorMsg(ctorMsg);
		params.setSecureContext("test_user0");
		chaincode.setParams(params);

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip1);
		url.append(":");
		url.append(port1);
		url.append(url5);
		System.out.println(url);

		String result = restfulService.post(url.toString(), chaincode);
		JSONObject json = JSON.parseObject(result);
		System.out.println(result);
		if (json.containsKey("result") && json.getJSONObject("result").getString("status").equals("OK")) {
			responseBody.setData(JSON.toJSONString(result));
			responseBody.setStatus(SUCCESS);
		} else {
			responseBody.setStatus(ERROR);
		}

		return JSON.toJSONString(responseBody);

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

	@RequestMapping(value = "query/{ccid}/{key}")
	public String test(@PathVariable("ccid") String ccid, @PathVariable("key") String key) {
		ChaincodeRequest chaincode = new ChaincodeRequest();
		chaincode.setId(1);
		chaincode.setJsonrpc("2.0");
		chaincode.setMethod("query");

		Params params = chaincode.new Params();
		ChaincodeRequest.ChaincodeID chaincodeId = chaincode.new ChaincodeID();
		ChaincodeRequest.CtorMsg ctorMsg = chaincode.new CtorMsg();
		chaincodeId.setName(ccid);
		ctorMsg.setArgs(new String[] { key });
		ctorMsg.setFunction("query");
		params.setChaincodeID(chaincodeId);
		params.setType(1);
		params.setCtorMsg(ctorMsg);
		params.setSecureContext("test_user0");
		chaincode.setParams(params);

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip1);
		url.append(":");
		url.append(port1);
		url.append(url5);
		System.out.println(url);

		String result = restfulService.post(url.toString(), chaincode);
		JSONObject json = JSON.parseObject(result);
		responseBody.setData(JSON.toJSONString(result));
		System.out.println(result);
		if (json.containsKey("result") && json.getJSONObject("result").getString("status").equals("OK")) {
			responseBody.setStatus(SUCCESS);
		} else {
			responseBody.setStatus(ERROR);
		}

		return JSON.toJSONString(responseBody);

	}
	
	@RequestMapping(value = "invoke/put/{ccid}")
	public String put(@PathVariable("ccid") String ccid, String key,String value) {
		ChaincodeRequest chaincode = new ChaincodeRequest();
		chaincode.setId(1);
		chaincode.setJsonrpc("2.0");
		chaincode.setMethod("invoke");

		Params params = chaincode.new Params();
		ChaincodeRequest.ChaincodeID chaincodeId = chaincode.new ChaincodeID();
		ChaincodeRequest.CtorMsg ctorMsg = chaincode.new CtorMsg();
		chaincodeId.setName(ccid);
		ctorMsg.setArgs(new String[] { key,value });
		ctorMsg.setFunction("put");
		params.setChaincodeID(chaincodeId);
		params.setType(1);
		params.setCtorMsg(ctorMsg);
		params.setSecureContext("test_user0");
		chaincode.setParams(params);

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip1);
		url.append(":");
		url.append(port1);
		url.append(url5);
		System.out.println(url);

		String result = restfulService.post(url.toString(), chaincode);
		JSONObject json = JSON.parseObject(result);
		responseBody.setData(JSON.toJSONString(result));
		System.out.println(result);
		if (json.containsKey("result") && json.getJSONObject("result").getString("status").equals("OK")) {
			responseBody.setStatus(SUCCESS);
		} else {
			responseBody.setStatus(ERROR);
		}

		return JSON.toJSONString(responseBody);

	}
	
	@RequestMapping(value = "invoke/delete/{ccid}")
	public String delete(@PathVariable("ccid") String ccid, String key,String value) {
		ChaincodeRequest chaincode = new ChaincodeRequest();
		chaincode.setId(1);
		chaincode.setJsonrpc("2.0");
		chaincode.setMethod("invoke");

		Params params = chaincode.new Params();
		ChaincodeRequest.ChaincodeID chaincodeId = chaincode.new ChaincodeID();
		ChaincodeRequest.CtorMsg ctorMsg = chaincode.new CtorMsg();
		chaincodeId.setName(ccid);
		ctorMsg.setArgs(new String[] { key,value });
		ctorMsg.setFunction("delete");
		params.setChaincodeID(chaincodeId);
		params.setType(1);
		params.setCtorMsg(ctorMsg);
		params.setSecureContext("test_user0");
		chaincode.setParams(params);

		ResponseBody responseBody = new ResponseBody();

		StringBuffer url = new StringBuffer();
		url.append(URL_PRE_HTTP);
		url.append(ip1);
		url.append(":");
		url.append(port1);
		url.append(url5);
		System.out.println(url);

		String result = restfulService.post(url.toString(), chaincode);
		JSONObject json = JSON.parseObject(result);
		responseBody.setData(JSON.toJSONString(result));
		System.out.println(result);
		if (json.containsKey("result") && json.getJSONObject("result").getString("status").equals("OK")) {
			responseBody.setStatus(SUCCESS);
		} else {
			responseBody.setStatus(ERROR);
		}

		return JSON.toJSONString(responseBody);

	}

}
