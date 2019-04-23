package com.sxt.sys.utils;


import java.util.List;

/**
 * layui数据表格的json适配类(简单表格数据的json格式)
 *
 */
public class DataGridView {
	
	private Integer code=0;

	private String msg="";

	private Long count;

	private List<?> data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public DataGridView(Long count, List<?> data) {
		this.count = count;
		this.data = data;
	}
}
