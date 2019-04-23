package com.sxt.sys.controller;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DTreeNodeBuilder;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    private static final String MODEL="dept/";

    /**
     * 加载左侧菜单树
     */
    @RequestMapping(MODEL+"loadDeptManagerLeftTreeJson")
    public DataGridView loadDeptManagerLeftTreeJson(DeptVo deptVo){
        //查询所有可用菜单
        deptVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Dept> queryAllDept = deptService.queryAllDeptForList(deptVo);
        List<DTreeNode> dTreeNodes=new ArrayList<>();
        for (Dept dept : queryAllDept) {
            Integer id=dept.getId();
            Integer parentId=dept.getPid();
            String title=dept.getName();
            Boolean spread=dept.getOpen()==SysConstast.STATUS_OPEN_TRUE?true:false;
            dTreeNodes.add(new DTreeNode(id, parentId, title, spread));
        }
        return new DataGridView(Long.valueOf(dTreeNodes.size()), dTreeNodes);
    }

    /**
     * 加载部门数据
     */
    @RequestMapping(MODEL+"loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo){
        return this.deptService.queryDataGridView(deptVo);
    }


    /**
     * 加载添加页面下拉菜单树(标准json)
     */
    @RequestMapping(MODEL+"loadDeptManageraccordionTreeJson")
    public DataGridView loadDeptManageraccordionTreeJson(DeptVo deptVo){
        deptVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Dept> depts = this.deptService.queryAllDeptForList(deptVo);
        List<DTreeNode> list = new ArrayList<>();
        for (Dept dept : depts) {
            Integer id=dept.getId();
            Integer parentId=dept.getPid();
            String title=dept.getName();
            Boolean spread=dept.getOpen()==SysConstast.STATUS_OPEN_TRUE?true:false;
            list.add(new DTreeNode(id, parentId, title, spread));
        }
        List<DTreeNode> build = DTreeNodeBuilder.build(list, 0);
        return new DataGridView(Long.valueOf(list.size()),build);
    }

    /**
     * 给排序码赋值（查询最大的排序码）
     */
    @RequestMapping(MODEL+"loadMaxOrderNumber")
    public Map<String,Integer> loadMaxOrderNumber(){
        Integer num = this.deptService.queryOrdernumCount();
        Map<String,Integer> map = new HashMap<>();
        map.put("ordernum",num+1);
        return map;
    }

    /**
     * 添加部门
     */
    @RequestMapping(MODEL+"addDept")
    public ResultObj addDept(DeptVo deptVo){
        deptVo.setCreatetime(new Date());
        try {
            deptService.addDept(deptVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 修改部门
     */
    @RequestMapping(MODEL+"updateDept")
    public ResultObj updateDept(DeptVo deptVo){
        deptVo.setCreatetime(new Date());
        try {
            deptService.updateDept(deptVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }

    /**
     * 查询是否有子节点
     */
    @RequestMapping(MODEL+"checkDeptChildren")
    public Map<String,Integer> checkDeptChildren(DeptVo deptVo){
        Integer childrenNum = deptService.checkDeptChildren(deptVo.getId());
        Map<String,Integer> map = new HashMap<>();
        if(childrenNum>0){
            map.put("code",1);
        }else{
            map.put("code",-1);
        }
        return map;
    }
    /**
     * 删除部门
     */
    @RequestMapping(MODEL+"deleteDept")
    public ResultObj deleteDept(DeptVo deptVo){
        try {
            deptService.deleteDept(deptVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }
}
