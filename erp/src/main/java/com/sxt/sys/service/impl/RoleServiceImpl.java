package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public DataGridView loadAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> roles = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),roles);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveExportPermission(Integer rid, Integer[] ids) {
        //根据roleid删除角色和权限之间的关系
        this.roleMapper.deleteRolePermissionById(rid);
        if(null!=rid&&ids.length>0){
            for (Integer pid : ids) {
                this.roleMapper.insertRolePermission(rid,pid);
            }
        }
    }

    @Override
    public DataGridView loadRoleByUseId(Integer id) {
        //查询全部角色
        Role role = new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(role);
        //根据用户id查询角色
        List<Role> roles = this.roleMapper.queryRoleByUseId(id);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Role r1 : allRole) {
            Boolean LAY_CHECKED=false;
            for (Role r2 : roles) {
                if(r1.getId()==r2.getId()){
                    LAY_CHECKED=true;
                    break;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("id",r1.getId());
            map.put("name",r1.getName());
            map.put("remark",r1.getRemark());
            map.put("LAY_CHECKED",LAY_CHECKED);
            list.add(map);
        }

        return new DataGridView(Long.valueOf(allRole.size()),list);
    }

    @Override
    public List<String> queryRoleByUserIdForList(Integer id) {
        List<String> list = new ArrayList<>();
        List<Role> role = this.roleMapper.queryRoleByUseId(id);
        for (Role roles : role) {
            list.add(roles.getName());
        }
        return list;
    }
}
