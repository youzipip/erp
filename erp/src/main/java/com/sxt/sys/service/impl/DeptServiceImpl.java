package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.mapper.DeptMapper;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.DeptVo;

@Service
@Transactional
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptMapper deptMapper;

	@Override
	@Transactional(readOnly = true)
	public List<Dept> queryAllDeptForList(DeptVo deptVo) {
		return this.deptMapper.queryAllDept(deptVo);
	}

	@Override
	@Transactional(readOnly = true)
	public DataGridView queryDataGridView(DeptVo deptVo) {
		Page<Object> page = PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
		List<Dept> depts = this.deptMapper.queryAllDept(deptVo);
		return new DataGridView(page.getTotal(),depts);
	}

	@Override
	public Integer queryOrdernumCount() {
		return this.deptMapper.queryMaxOrderNum();
	}

	@Override
	public void addDept(DeptVo deptVo) {
		this.deptMapper.insertSelective(deptVo);
	}

	@Override
	public void updateDept(DeptVo deptVo) {
		this.deptMapper.insertSelective(deptVo);
	}

	@Override
	public Integer checkDeptChildren(Integer id) {
		return this.deptMapper.checkDeptChildrenNum(id);
	}

	@Override
	public void deleteDept(Integer id) {
		this.deptMapper.deleteByPrimaryKey(id);
	}
}
