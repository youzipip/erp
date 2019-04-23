package com.sxt.bus.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 中转路由
 */
@Controller
public class busController {

    private static final String MODEL="bus/";
    /**
     * 跳转到客户管理
     */
    @RequestMapping(MODEL+"toCustomerManager")
    public String toCustumerManager() {
        return "business/customer/customerManager";
    }
    /**
     * 跳转到客户管理
     */
    @RequestMapping(MODEL+"toGoodsManager")
    public String toGoodsManager() {
        return "business/goods/goodsManager";
    }
    /**
     * 跳转到进货管理页面
     */
    @RequestMapping(MODEL+"toImportManager")
    public String toInportManager(){
        return "business/import/importManager";
    }

    /**
     * 跳转到进货管理left页面
     */
    @RequestMapping(MODEL+"toImportLeftManager")
    public String toProviderLeftManager(){
        return "business/import/importLeftManager";
    }

    /**
     * 跳转到进货管理right页面
     */
    @RequestMapping(MODEL+"toImportRightManager")
    public String toProviderRightManager(){
        return "business/import/importRightManager";
    }


}
