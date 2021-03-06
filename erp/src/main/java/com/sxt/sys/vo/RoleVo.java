package com.sxt.sys.vo;

import com.sxt.sys.domain.Role;

public class RoleVo extends Role {

    private Integer page;
    private Integer limit;
    //选中的菜单节点id
    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

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
