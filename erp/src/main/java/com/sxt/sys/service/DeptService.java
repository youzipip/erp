package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Dept;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.DeptVo;

/**
 * 部门管理服务接口
 *
 */
public interface DeptService {

	/**
	 * 查询所有部门返回List
	 */
	List<Dept> queryAllDeptForList(DeptVo deptVo);
	/**
	 * 分页查询
	 */
	DataGridView queryDataGridView(DeptVo deptVo);

	/**
	 * 查询最大排序码
	 */
    Integer queryOrdernumCount();
	/**
	 * 添加部门
	 */
	void addDept(DeptVo deptVo);
	/**
	 * 修改部门
	 */
	void updateDept(DeptVo deptVo);

	/**
	 *判断部门下是否有子节点
	 */
	Integer checkDeptChildren(Integer id);

	/**
	 *删除部门
	 */
	void deleteDept(Integer id);
}
