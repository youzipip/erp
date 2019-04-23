package com.sxt.sys.constast;

/**
 * 常量配置类
 * 
 * @author LJH
 *
 */
public interface SysConstast {

	/**
	 * 用户类型
	 */
	Integer USER_TYPE_SUPER = 0; // 超级用户
	Integer USER_TYPE_NORMAL = 1;// 系统用户

	/**
	 * 可用状态
	 */
	Integer AVAILABLE_TRUE = 1;// 可用
	Integer AVAILABLE_FALSE = 0;// 不可用

	/**
	 * 菜单权限类型
	 */
	String TYPE_PERMISSION_MENU = "menu";
	String TYPE_PERMISSION_PERMISSION = "permission";

	/**
	 * 展开状态
	 */
	Integer STATUS_OPEN_TRUE = 1;
	Integer STATUS_OPEN_FALSE = 0;

	// 用户操作数据的常量
	Integer CODE_OK = 200;
	Integer CODE_ERROR = -1;
	String OPERATE_DELETE_SUCCESS = "删除成功";
	String OPERATE_DELETE_ERROR = "删除失败";

	String OPERATE_ADD_SUCCESS = "添加成功";
	String OPERATE_ADD_ERROR = "添加失败";

	String OPERATE_UPDATE_SUCCESS = "修改成功";
	String OPERATE_UPDATE_ERROR = "修改失败";

	String OPERATE_DISPATCH_SUCCESS = "重置成功";
	String OPERATE_DISPATCH_ERROR = "重置失败";

	String OPERATE_RESET_SUCCESS = "分配成功";
	String OPERATE_RESET_ERROR = "分配失败";

	//用户默认密码
	String USER_DEFAULT_PWD="123456";
	//用户默认头像
	String USER_DEFAULT_IMGPATH="../resources/images/defaultusertitle.jpg";
}
