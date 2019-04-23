package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Logger;
import com.sxt.sys.mapper.LoggerMapper;
import com.sxt.sys.service.LoggerService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LoggerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private LoggerMapper loggerMapper;

    @Override
    public DataGridView loadAllLogger(LoggerVo loggerVo) {
        Page<Object> page = PageHelper.startPage(loggerVo.getPage(), loggerVo.getLimit());
        List<Logger> loggers = this.loggerMapper.queryAllLogger(loggerVo);
        return new DataGridView(page.getTotal(),loggers);
    }

    @Override
    public void deleteLogger(Integer id) {
        this.loggerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addLogger(LoggerVo loggerVo) {
        this.loggerMapper.insertSelective(loggerVo);
    }
}
