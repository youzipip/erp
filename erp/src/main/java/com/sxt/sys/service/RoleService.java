package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    /**
     * 查询所有角色
     */
    DataGridView loadAllRole(RoleVo roleVo);

    /**
     * 添加角色
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     */
    void updateRole(RoleVo roleVo);

    /**
     * 修改角色
     */
    void deleteRole(Integer id);

    /**
     *保存角色和权限之间的关系
     */
    void saveExportPermission(Integer rid, Integer[] ids);

    /**
     * 根据用户id查询角色
     */
    DataGridView loadRoleByUseId(Integer id);

    //根据用户id查询角色
    List<String> queryRoleByUserIdForList(Integer id);

}
