package com.sxt.sys.service;

import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;

import java.util.List;

/**
 * 用户服务接口
 *
 */
public interface UserService {

	/**
	 * 根据登陆名查询用户
	 */
	User queryUserByLoginName(String loginName);

	/**
	 * 查询所有用户返回List
	 */
	List<User> queryAllUserForList(UserVo userVo);
	/**
	 * 分页查询
	 */
	DataGridView queryDataGridView(UserVo userVo);

	/**
	 * 查询最大排序码
	 */
	Integer queryOrdernumCount();
	/**
	 * 添加用户
	 */
	void addUser(UserVo userVo);
	/**
	 * 修改用户
	 */
	void updateUser(UserVo userVo);

	/**
	 *删除用户
	 */
	void deleteUser(Integer id);

	/**
	 *根据部门id查询部门下的员工
	 */
	List<User> loadUserById(Integer deptid);

	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
    User loadUserByLeaderId(Integer id);

	/**
	 * 重置密码
	 * @param id
	 */
	void resetPwd(String pwd,Integer id);

	/**
	 * 保存用户角色之间的关系
	 * @param id
	 * @param rid
	 */
	void saveUserRole(Integer id, Integer[] rid);

	/**
	 * 根据用户id查询
	 * @param id
	 * @return
	 */
    User queryUserById(Integer id);
}
