package com.sxt.sys.controller;

import com.sxt.sys.domain.User;
import com.sxt.sys.service.NoticeService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    private static final String MODEL="notice/";

    /**
     * 查询所有公告
     */
    @RequestMapping(MODEL+"loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        return this.noticeService.loadAllNotice(noticeVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping(MODEL+"addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        noticeVo.setCreatetime(new Date());
        //取出登录对象
        User user = (User) WebUtils.getCurrentSession().getAttribute("user");
        noticeVo.setOpername(user.getRemark());
        try {
            this.noticeService.addNotice(noticeVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }
    /**
     * 修改公告
     */
    @RequestMapping(MODEL+"updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try {
            this.noticeService.updateNotice(noticeVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }
    /**
     * 删除公告
     */
    @RequestMapping(MODEL+"deleteNotice")
    public ResultObj deleteNotice(NoticeVo noticeVo){
        try {
            this.noticeService.deleteNotice(noticeVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }
}
