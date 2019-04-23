package com.sxt.bus.vo;

import com.sxt.bus.domain.Provider;

public class ProviderVo extends Provider {

    private Integer page;

    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
