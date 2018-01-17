package com.springboot.util.result;

import java.io.Serializable;

public class Result implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Object data;
	private int res;
	private String msg;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
