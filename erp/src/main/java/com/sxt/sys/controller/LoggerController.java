package com.sxt.sys.controller;

import com.sxt.sys.service.LoggerService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.LoggerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.DatabaseMetaData;

@RestController
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    private static final String MODEL="logger/";

    /**
     * 查询日志
     * @param loggerVo
     * @return
     */
    @RequestMapping(MODEL+"loadAllLogger")
    public DataGridView loadAllLogger(LoggerVo loggerVo){
        return this.loggerService.loadAllLogger(loggerVo);
    }

    /**
     * 删除日志
     */
    @RequestMapping(MODEL+"deleteLogger")
    public ResultObj deleteLogger(LoggerVo loggerVo){
        try {
            this.loggerService.deleteLogger(loggerVo.getId());
            return ResultObj.deleteOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.deleteError();
        }
    }
}
