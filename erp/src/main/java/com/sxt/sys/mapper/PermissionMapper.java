package com.sxt.sys.mapper;

import com.sxt.sys.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    //查询所有权限或菜单
    List<Permission> queryAllPermission(Permission permission);

    /**
     *查询最大排序码
     */
    Integer queryMaxOrderNum();

    /**
     *查询子节点
     *  */
    Integer checkPermissionChildrenNum(Integer id);

    /**
     *根据角色id查询菜单和权限
     */
    List<Permission> queryPermissionByRoleId(Integer id);

    /**
     * 根据用户id查询菜单和权限
     */
    List<Permission> queryPermissionByUserId(Integer userid,String type);
}