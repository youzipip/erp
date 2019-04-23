package com.sxt.sys.controller;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.*;
import com.sxt.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    private static final String MODEL="user/";

    /**
     * 加载用户数据
     */
    @RequestMapping(MODEL+"loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        return this.userService.queryDataGridView(userVo);
    }

    /**
     * 给排序码赋值（查询最大的排序码）
     */
    @RequestMapping(MODEL+"loadMaxOrderNumber")
    public Map<String,Integer> loadMaxOrderNumber(){
        Integer num = this.userService.queryOrdernumCount();
        Map<String,Integer> map = new HashMap<>();
        map.put("ordernum",num+1);
        return map;
    }

    /**
     * 添加用户
     */
    @RequestMapping(MODEL+"addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            //用户类型
            userVo.setType(SysConstast.USER_TYPE_NORMAL);
            //盐
            userVo.setSalt(RandomUtils.createRandomStringUserUUID());
            //默认密码存入密文
            userVo.setPwd(MD5Utils.Md5Pssword(SysConstast.USER_DEFAULT_PWD,RandomUtils.createRandomStringUserUUID()));
            //默认头像
            userVo.setImgpath(SysConstast.USER_DEFAULT_IMGPATH);
            userService.addUser(userVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping(MODEL+"updateUser")
    public ResultObj updateUser(UserVo userVo){
        try {
            userService.updateUser(userVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }
    /**
     * 删除用户
     */
    @RequestMapping(MODEL+"deleteUser")
    public ResultObj deleteUser(UserVo userVo){
        try {
            userService.deleteUser(userVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }
    /**
     * 根据部门id查询部门下的用户
     */
    @RequestMapping(MODEL+"loadUserById")
    public List<User> loadUserById(UserVo userVo){
        return userService.loadUserById(userVo.getDeptid());
    }

    /**
     * 汉字转拼音
     */
    @RequestMapping(MODEL+"changeUserNameToPinyinStr")
    public Map<String,String> changeUserNameToPinyinStr(String name){
        String s = PinYinUtils.toPinyin(name);
        Map<String,String> map  = new HashMap<>();
        map.put("name",s);
        return map;
    }

    /**
     *根据用户id查询用户
     */
    @RequestMapping(MODEL+"loadUserByLeaderId")
    public User loadUserByLeaderId(UserVo userVo){
        return userService.loadUserByLeaderId(userVo.getId());
    }

    /**
     * 重置密码
     */
    @RequestMapping(MODEL+"resetPwd")
    public ResultObj resetPwd(UserVo userVo){
        try {
            userVo.setPwd(MD5Utils.Md5Pssword(SysConstast.USER_DEFAULT_PWD,RandomUtils.createRandomStringUserUUID()));
            this.userService.resetPwd(userVo.getPwd(),userVo.getId());
            return ResultObj.operateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.operateError();
        }
    }

    /**
     * 保存用户角色之间的关系
     */
    @RequestMapping(MODEL+"saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            this.userService.saveUserRole(userVo.getId(),userVo.getRid());
            return ResultObj.resetOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.resetError();
        }
    }
    /**
     * 验证密码
     */
    @RequestMapping(MODEL+"queryUserByPwd")
    public ResultObj queryUserByPwd(String pwd){
        //得到当前用户对象
        User user = (User) WebUtils.getCurrentSession().getAttribute("user");
        User u = this.userService.queryUserById(user.getId());
        if(u.getPwd().equals(MD5Utils.Md5Pssword(pwd,u.getSalt()))){
            return new ResultObj(1,"");
        }else{
            return new ResultObj(-1,"");
        }
    }
    /**
     * 修改密码
     */
    @RequestMapping(MODEL+"changePwd")
    public ResultObj changePwd(UserVo userVo){
        //得到当前用户对象
        User user = (User) WebUtils.getCurrentSession().getAttribute("user");
        userVo.setId(user.getId());
        userVo.setPwd(MD5Utils.Md5Pssword(userVo.getPwd(),user.getSalt()));
        try {
            this.userService.updateUser(userVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }


}
