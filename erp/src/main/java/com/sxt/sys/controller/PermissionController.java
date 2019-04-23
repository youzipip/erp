package com.sxt.sys.controller;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    private static final String MODEL="permission/";

    /**
     * 查询所有权限
     */
    @RequestMapping(MODEL+"loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo){
        permissionVo.setType(SysConstast.TYPE_PERMISSION_PERMISSION);
        return this.permissionService.queryDataGridView(permissionVo);
    }

    /**
     * 给排序码赋值（查询最大的排序码）
     */
    @RequestMapping(MODEL+"loadMaxOrderNumber")
    public Map<String,Integer> loadMaxOrderNumber(){
        Integer num = this.permissionService.queryOrdernumCount();
        Map<String,Integer> map = new HashMap<>();
        map.put("ordernum",num+1);
        return map;
    }

    /**
     * 添加权限
     */
    @RequestMapping(MODEL+"addPermission")
    public ResultObj addPermission(PermissionVo permissionVo){
        try {
            permissionService.addPermission(permissionVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 修改权限
     */
    @RequestMapping(MODEL+"updatePermission")
    public ResultObj updatePermission(PermissionVo permissionVo){
        try {
            permissionService.updatePermission(permissionVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }

    /**
     * 删除权限
     */
    @RequestMapping(MODEL+"deletePermission")
    public ResultObj deletePermission(PermissionVo permissionVo){
        try {
            permissionService.deletePermission(permissionVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }

}
