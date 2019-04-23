package com.sxt.bus.controller;

import com.sxt.bus.service.CustomerService;

import com.sxt.bus.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;

/**
 * 客户管理控制器
 *
 */
@RestController
public class CustomerController {
	
	private static final String MODEL="customer/";

	@Autowired
	private CustomerService customerService;


	/**
	 * 加载客户管理列表
	 */
	@RequestMapping(MODEL+"loadAllCustomer")
	public DataGridView loadAllCustomer(CustomerVo customerVo) {
		return this.customerService.queryAllCustomer(customerVo);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(MODEL+"addCustomer")
	public ResultObj addCustomer(CustomerVo customerVo) {
		try {
			this.customerService.addCustomer(customerVo);
			return ResultObj.addOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.addError();
		}
	}
	/**
	 * 修改
	 */
	@RequestMapping(MODEL+"updateCustomer")
	public ResultObj updateCustomer(CustomerVo customerVo) {
		try {
			this.customerService.updateCustomer(customerVo);
			return ResultObj.updateOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.updateError();
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping(MODEL+"deleteCustomer")
	public ResultObj deleteCustomer(CustomerVo customerVo) {
		try {
			this.customerService.deleteCustomer(customerVo.getId());
			return ResultObj.deleteOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.deleteError();
		}
	}
}
