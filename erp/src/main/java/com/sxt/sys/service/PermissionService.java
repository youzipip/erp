package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Permission;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.DeptVo;
import com.sxt.sys.vo.PermissionVo;

/**
 * 菜单和权限的服务接口
 *
 */
public interface PermissionService {
	
	/**
	 * 查询所有可用的菜单或权限返回 List
	 */
	List<Permission> queryAllPermission(PermissionVo permissionVo);
	
	/**
	 * 根据用户ID查询权限或菜单返回List
	 */
	List<Permission> queryAllPermissionById(PermissionVo permissionVo,Integer id);

	/**
	 * 分页查询
	 */
	DataGridView queryDataGridView(PermissionVo permissionVo);

	/**
	 * 查询最大排序码
	 */
	Integer queryOrdernumCount();
	/**
	 * 添加菜单
	 */
	void addPermission(PermissionVo permissionVoVo);
	/**
	 * 修改菜单
	 */
	void updatePermission(PermissionVo permissionVo);

	/**
	 *判断菜单下是否有子节点
	 */
	Integer checkPermissionChildren(Integer id);

	/**
	 *删除菜单
	 */
	void deletePermission(Integer id);

	/**
	 *根据角色id查询菜单和权限
	 */
    List<Permission> queryPermissionByRoleId(Integer id);
	//根据用户id查询权限
	List<String> queryPermissionByUserIdForList(Integer id);
}
