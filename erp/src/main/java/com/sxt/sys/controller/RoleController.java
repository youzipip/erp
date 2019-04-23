package com.sxt.sys.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.PermissionVo;
import com.sxt.sys.vo.RoleVo;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private static final String MODEL="role/";

    /**
     * 加载角色数据
     */
    @RequestMapping(MODEL+"loadAllRole")
    public DataGridView loadAllMenu(RoleVo roleVo){
        return this.roleService.loadAllRole(roleVo);
    }
    
    /**
     * 添加角色
     */
    @RequestMapping(MODEL+"addRole")
    public ResultObj addMenu(RoleVo roleVo){
        roleVo.setCreatetime(new Date());
        try {
            roleService.addRole(roleVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping(MODEL+"updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            roleService.updateRole(roleVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping(MODEL+"deleteRole")
    public ResultObj deleteRole(RoleVo roleVo){
        try {
            roleService.deleteRole(roleVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }

    /**
     * 加载复选框菜单树(list类型)
     */
    @RequestMapping(MODEL+"initPermissionTree")
    public DataGridView initPermissionTree(RoleVo roleVo){
        //查询所有菜单和权限
        PermissionVo permissionVo = new PermissionVo();
        permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Permission> permissions = permissionService.queryAllPermission(permissionVo);
        //根据角色id查询菜单和权限
        List<Permission> permissionList = permissionService.queryPermissionByRoleId(roleVo.getId());
        List<DTreeNode> nodes = new ArrayList<>();
        for (Permission p1 : permissions) {
            //默认不选中
            String checkArr = "0";
            for (Permission p2 : permissionList) {
                //角色拥有该权限则选中
                if(p1.getId()==p2.getId()){
                    checkArr = "1";
                    break;
                }
            }
            Integer id = p1.getId();
            Integer parentId = p1.getPid();
            String title = p1.getName();
            Boolean spread = p1.getOpen()==SysConstast.STATUS_OPEN_TRUE?true:false;
            nodes.add(new DTreeNode(id,parentId,title,checkArr,spread));
        }
        return new DataGridView(Long.valueOf(nodes.size()),nodes);
    }

    /**
     *保存角色和权限之间的关系
     */
    @RequestMapping(MODEL+"saveExportPermission")
    public ResultObj saveExportPermission(RoleVo roleVo){
        try {
            this.roleService.saveExportPermission(roleVo.getId(),roleVo.getIds());
            return ResultObj.resetOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.resetError();
        }
    }

    /**
     * 根据用户id查询角色
     */
    @RequestMapping(MODEL+"loadRoleByUserId")
    public DataGridView loadRoleByUserId(Integer id){
        return this.roleService.loadRoleByUseId(id);
    }
}
