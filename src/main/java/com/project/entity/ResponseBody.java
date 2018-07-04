package com.project.entity;

import java.util.List;

public class ResponseBody {
	private String data;
	private List<?> list;

	private String status;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	};
}
