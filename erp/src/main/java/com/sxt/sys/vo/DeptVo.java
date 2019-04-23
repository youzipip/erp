package com.sxt.sys.vo;

import com.sxt.sys.domain.Dept;

public class DeptVo extends Dept {

	private Integer page; //当前页

	private Integer limit; //每页条数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
