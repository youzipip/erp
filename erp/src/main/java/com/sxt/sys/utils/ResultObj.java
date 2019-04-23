package com.sxt.sys.utils;

import com.sxt.sys.constast.SysConstast;

/**
 * 响应到页面的json对象的构造类
 * @author LJH
 *
 */
public class ResultObj {
	
	private Integer code;
	private String msg;
	
	/**
	 * 删除成功
	 * @return
	 */
	public static ResultObj deleteOk(){
		return new ResultObj(SysConstast.CODE_OK, SysConstast.OPERATE_DELETE_SUCCESS);
	}
	/**
	 * 删除失败
	 * @return
	 */
	public static ResultObj deleteError(){
		return new ResultObj(SysConstast.CODE_ERROR, SysConstast.OPERATE_DELETE_ERROR);
	}
	
	/**
	 * 更新成功
	 * @return
	 */
	public static ResultObj updateOk(){
		return new ResultObj(SysConstast.CODE_OK, SysConstast.OPERATE_UPDATE_SUCCESS);
	}
	/**
	 * 更新失败
	 * @return
	 */
	public static ResultObj updateError(){
		return new ResultObj(SysConstast.CODE_ERROR, SysConstast.OPERATE_UPDATE_ERROR);
	}
	
	/**
	 * 添加成功
	 * @return
	 */
	public static ResultObj addOk(){
		return new ResultObj(SysConstast.CODE_OK, SysConstast.OPERATE_ADD_SUCCESS);
	}
	/**
	 * 添加失败
	 * @return
	 */
	public static ResultObj addError(){
		return new ResultObj(SysConstast.CODE_ERROR, SysConstast.OPERATE_ADD_ERROR);
	}
	
	/**
	 * 分配成功
	 * @return
	 */
	public static ResultObj operateOk(){
		return new ResultObj(SysConstast.CODE_OK, SysConstast.OPERATE_DISPATCH_SUCCESS);
	}
	/**
	 * 分配失败
	 * @return
	 */
	public static ResultObj operateError(){
		return new ResultObj(SysConstast.CODE_ERROR, SysConstast.OPERATE_DISPATCH_ERROR);
	}
	
	/**
	 * 重置成功
	 * @return
	 */
	public static ResultObj resetOk(){
		return new ResultObj(SysConstast.CODE_OK, SysConstast.OPERATE_RESET_SUCCESS);
	}
	/**
	 * 重置失败
	 * @return
	 */
	public static ResultObj resetError(){
		return new ResultObj(SysConstast.CODE_ERROR, SysConstast.OPERATE_RESET_ERROR);
	}
	
	public ResultObj(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
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
	
	

}
