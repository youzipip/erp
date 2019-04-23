package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Logger;
import com.sxt.sys.mapper.LoggerMapper;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.LoggerVo;
import com.sxt.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoggerMapper loggerMapper;
	
	@Override
	@Transactional(readOnly=true)
	public User queryUserByLoginName(String loginName) {
		return userMapper.queryUserByUserLoginName(loginName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> queryAllUserForList(UserVo userVo) {
		return this.userMapper.queryAllUser(userVo);
	}

	@Override
	public DataGridView queryDataGridView(UserVo userVo) {
		Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
		List<User> users = this.userMapper.queryAllUser(userVo);
		return new DataGridView(page.getTotal(),users);
	}

	@Override
	public Integer queryOrdernumCount() {
		return this.userMapper.queryMaxOrderNum();
	}

	@Override
	public void addUser(UserVo userVo) {
		this.userMapper.insertSelective(userVo);
	}

	@Override
	public void updateUser(UserVo userVo) {
		this.userMapper.updateByPrimaryKeySelective(userVo);
	}

	@Override
	public void deleteUser(Integer id) {
		//删除用户和角色之间的关系
		this.userMapper.deleteUserRoleById(id);
		//删除用户
		this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> loadUserById(Integer deptid) {
		return this.userMapper.queryUserById(deptid);
	}

	@Override
	public User loadUserByLeaderId(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void resetPwd(String pwd,Integer id) {
		this.userMapper.resetUserPwd(pwd,id);
	}

	@Override
	public void saveUserRole(Integer id, Integer[] rid) {
		//删除用户角色之间的关系
		this.userMapper.deleteUserRoleById(id);
		if(null!=rid&&rid.length>0){
			for (Integer integer : rid) {
				this.userMapper.saveUserRole(id,integer);
			}
		}
	}

	@Override
	public User queryUserById(Integer id) {
		return this.userMapper.queryUserByUserId(id);
	}
}
