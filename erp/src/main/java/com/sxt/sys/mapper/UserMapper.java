package com.sxt.sys.mapper;

import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //根据用户登陆名查询用户
    User queryUserByUserLoginName(String loginname);
    //查询所有用户
    List<User> queryAllUser(User user);
    //查询最大排序码
    Integer queryMaxOrderNum();
    //根据部门id查询部门下的员工
    List<User> queryUserById(Integer deptid);
    //删除用户和角色之间的关系
    void deleteUserRoleById(Integer id);
    //重置密码
    void resetUserPwd(String pwd,Integer id);
    //保存用户角色之间的关系
    void saveUserRole(Integer id, Integer rid);
    //根据id查询角色
    User queryUserByUserId(Integer id);
}