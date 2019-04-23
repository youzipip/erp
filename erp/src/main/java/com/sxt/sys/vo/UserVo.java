package com.sxt.sys.vo;

import com.sxt.sys.domain.User;

public class UserVo extends User {

    private Integer page;

    private Integer limit;
    //角色id
    private Integer[] rid;

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

    public Integer[] getRid() {
        return rid;
    }

    public void setRid(Integer[] rid) {
        this.rid = rid;
    }
}
