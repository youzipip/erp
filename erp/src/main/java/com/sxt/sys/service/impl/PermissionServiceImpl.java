package com.sxt.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.sys.domain.Permission;
import com.sxt.sys.mapper.PermissionMapper;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.vo.PermissionVo;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	@Transactional(readOnly = true)
	public List<Permission> queryAllPermission(PermissionVo permissionVo) {
		return permissionMapper.queryAllPermission(permissionVo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Permission> queryAllPermissionById(PermissionVo permissionVo, Integer id) {
		return permissionMapper.queryPermissionByUserId(id,permissionVo.getType());
	}

	@Override
	@Transactional(readOnly = true)
	public DataGridView queryDataGridView(PermissionVo permissionVo) {
		Page<Object> page = PageHelper.startPage(permissionVo.getPage(), permissionVo.getLimit());
		List<Permission> permissions = this.permissionMapper.queryAllPermission(permissionVo);
		return new DataGridView(page.getTotal(),permissions);
	}

	@Override
	public Integer queryOrdernumCount() {
		return permissionMapper.queryMaxOrderNum();
	}

	@Override
	public void addPermission(PermissionVo permissionVoVo) {
		this.permissionMapper.insertSelective(permissionVoVo);
	}

	@Override
	public void updatePermission(PermissionVo permissionVo) {
		this.permissionMapper.updateByPrimaryKeySelective(permissionVo);
	}

	@Override
	public Integer checkPermissionChildren(Integer id) {
		return permissionMapper.checkPermissionChildrenNum(id);
	}

	@Override
	public void deletePermission(Integer id) {
		this.permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Permission> queryPermissionByRoleId(Integer id) {
		return this.permissionMapper.queryPermissionByRoleId(id);
	}

	@Override
	public List<String> queryPermissionByUserIdForList(Integer id) {
		List<Permission> permissions = this.permissionMapper.queryPermissionByUserId(id, SysConstast.TYPE_PERMISSION_PERMISSION);
		List<String> list = new ArrayList<>();
		for (Permission permission : permissions) {
			list.add(permission.getPercode());
		}
		return list;
	}
}
