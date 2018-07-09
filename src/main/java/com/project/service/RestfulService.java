package com.project.service;

import java.util.HashMap;

public interface RestfulService {
	abstract String get(String url, HashMap<String, Object> param);

	abstract String post(String url, Object param);
}
