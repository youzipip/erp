package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LoggerVo;

public interface LoggerService {

    /**
     * 查询所有公告
     */
    DataGridView loadAllLogger(LoggerVo loggerVo);
    
    void deleteLogger(Integer id);

    //添加
    void addLogger(LoggerVo loggerVo);
}
