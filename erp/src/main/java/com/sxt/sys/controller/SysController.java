package com.sxt.sys.controller;

import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForYear;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 做中转路由
 *
 */
@Controller
public class SysController {

	private static final String MODEL="sys/";
	/**
	 * 跳转到工作台
	 */
	@RequestMapping(MODEL+"toDeskManager")
	public String toDeskManager(){
		return "system/main";
	}

	/**
	 * 跳转到部门管理页面
	 */
	@RequestMapping(MODEL+"toDeptManager")
	public String toDeptManager(){
		return "system/dept/deptManager";
	}

	/**
	 * 跳转到部门管理left页面
	 */
	@RequestMapping(MODEL+"toDeptLeftManager")
	public String toDeptLeftManager(){
		return "system/dept/deptLeftManager";
	}
	/**
	 * 跳转到部门管理right页面
	 */
	@RequestMapping(MODEL+"toDeptRightManager")
	public String toDeptRightManager(){
		return "system/dept/deptRightManager";
	}
	/**
	 * 跳转到菜单管理
	 */
	@RequestMapping(MODEL+"toMenuManager")
	public String toMenuManager(){
		return "system/menu/menuManager";
	}
	/**
	 * 跳转到菜单管理left页面
	 */
	@RequestMapping(MODEL+"toMenuLeftManager")
	public String toMenuLeftManager(){
		return "system/menu/menuLeftManager";
	}
	/**
	 * 跳转到菜单管理right页面
	 */
	@RequestMapping(MODEL+"toMenuRightManager")
	public String toMenuRightManager(){
		return "system/menu/menuRightManager";
	}
	/**
	 * 跳转到系统公告页面
	 */
	@RequestMapping(MODEL+"toNoticeManager")
	public String toNoticeManager(){
		return "system/notice/noticeManager";
	}
	/**
	 * 跳转到权限管理
	 */
	@RequestMapping(MODEL+"toPermissionManager")
	public String toPermissionManager(){
		return "system/permission/permissionManager";
	}
	/**
	 * 跳转到权限管理left页面
	 */
	@RequestMapping(MODEL+"toPermissionLeftManager")
	public String toPermissionLeftManager(){
		return "system/permission/permissionLeftManager";
	}
	/**
	 * 跳转到权限管理right页面
	 */
	@RequestMapping(MODEL+"toPermissionRightManager")
	public String toPermissionRightManager(){
		return "system/permission/permissionRightManager";
	}
	/**
	 * 跳转到角色管理页面
	 */
	@RequestMapping(MODEL+"toRoleManager")
	public String toRoleManager(){
		return "system/role/roleManager";
	}
	/**
	 * 跳转到用户管理页面
	 */
	@RequestMapping(MODEL+"toUserManager")
	public String toUserManager(){
		return "system/user/userManager";
	}
	/**
	 * 跳转到用户管理left页面
	 */
	@RequestMapping(MODEL+"toUserLeftManager")
	public String toUserLeftManager(){
		return "system/user/userLeftManager";
	}
	/**
	 * 跳转到用户管理right页面
	 */
	@RequestMapping(MODEL+"toUserRightManager")
	public String toUserRightManager(){
		return "system/user/userRightManager";
	}

	/**
	 * 跳转到日志管理页面
	 */
	@RequestMapping(MODEL+"toLogInfoManager")
	public String toLogInfoManager(){
		return "system/logger/loggerManager";
	}

	/**
	 * 跳转到修改密码页面
	 */
	@RequestMapping(MODEL+"toChangePwd")
	public String toChangePwd(){
		return "system/user/changePwd";
	}
}
