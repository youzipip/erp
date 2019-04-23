package com.sxt.sys.mapper;

import com.sxt.sys.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //查询所有角色
    List<Role> queryAllRole(Role role);
    //保存角色权限关系
    void insertRolePermission(Integer id, Integer pid);
    //根据角色id删除角色和权限关系
    void deleteRolePermissionById(Integer rid);
    //根据用户id查询角色
    List<Role> queryRoleByUseId(Integer id);
}