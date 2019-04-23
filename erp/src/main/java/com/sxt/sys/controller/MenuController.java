package com.sxt.sys.controller;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.utils.*;
import com.sxt.sys.vo.DeptVo;
import com.sxt.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MenuController {

    private static final String MODEL = "menu/";
    @Autowired
    private PermissionService permissionService;

    /**
     * 加载左侧菜单树
     */
    @RequestMapping(MODEL+"loadIndexLeftMenuJosn")
    public List<TreeNode> loadIndexLeftMenuJosn(PermissionVo permissionVo){
        //得到当前登录用户对象
        User user = (User) WebUtils.getCurrentSession().getAttribute("user");
        //可用菜单和用户
        permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        permissionVo.setType(SysConstast.TYPE_PERMISSION_MENU);
        List<Permission> permissions;

        if(user.getType()== SysConstast.USER_TYPE_SUPER){
            permissions = this.permissionService.queryAllPermission(permissionVo);
        }else{
            permissions = this.permissionService.queryAllPermissionById(permissionVo,user.getId());
        }
        List<TreeNode> treeNodes = new ArrayList<>();

        for (Permission p : permissions) {
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getName();
            String icon = p.getIcon();
            String href = p.getHref();
            Boolean spread = p.getOpen()==SysConstast.STATUS_OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        return TreeNodeBuilder.build(treeNodes,1);
    }

    /**
     * 加载左侧菜单树(标准json)
     */
    @RequestMapping(MODEL+"loadPermissionManagerTreeJson")
    public DataGridView loadPermissionManageraccordionTreeJson(PermissionVo permissionVo){
        permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        permissionVo.setType(SysConstast.TYPE_PERMISSION_MENU);
        List<Permission> permissions = this.permissionService.queryAllPermission(permissionVo);
        List<DTreeNode> list = new ArrayList<>();
        for (Permission p : permissions) {
            Integer id=p.getId();
            Integer parentId=p.getPid();
            String title=p.getName();
            Boolean spread=p.getOpen()==SysConstast.STATUS_OPEN_TRUE?true:false;
            list.add(new DTreeNode(id, parentId, title, spread));
        }
        List<DTreeNode> build = DTreeNodeBuilder.build(list, 0);
        return new DataGridView(Long.valueOf(list.size()),build);
    }

    /**
     * 加载菜单数据
     */
    @RequestMapping(MODEL+"loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo){
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
     * 添加菜单
     */
    @RequestMapping(MODEL+"addMenu")
    public ResultObj addMenu(PermissionVo permissionVo){
        try {
            permissionService.addPermission(permissionVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 修改菜单
     */
    @RequestMapping(MODEL+"updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo){
        try {
            permissionService.updatePermission(permissionVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }

    /**
     * 查询是否有子节点
     */
    @RequestMapping(MODEL+"checkMenuChildren")
    public Map<String,Integer> checkMenuChildren(PermissionVo permissionVo){
        Integer childrenNum = permissionService.checkPermissionChildren(permissionVo.getId());
        Map<String,Integer> map = new HashMap<>();
        if(childrenNum>0){
            map.put("code",1);
        }else{
            map.put("code",-1);
        }
        return map;
    }
    /**
     * 删除菜单
     */
    @RequestMapping(MODEL+"deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo){
        try {
            permissionService.deletePermission(permissionVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }



}
