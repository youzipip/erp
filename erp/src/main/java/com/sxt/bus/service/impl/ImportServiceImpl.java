package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Import;
import com.sxt.bus.mapper.ImportMapper;
import com.sxt.bus.service.ImportService;
import com.sxt.bus.vo.ImportVo;
import com.sxt.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private ImportMapper importMapper;

    @Override
    public DataGridView loadAllImport(ImportVo importVo) {
        Page<Object> page = PageHelper.startPage(importVo.getPage(), importVo.getLimit());
        List<Import> list = this.importMapper.queryAllImport(importVo);
        return new DataGridView(page.getTotal(),list);
    }

    @Override
    public void addImport(ImportVo importVo) {
        this.importMapper.insertSelective(importVo);
    }

    @Override
    public void updateImport(ImportVo importVo) {
        this.importMapper.updateByPrimaryKeySelective(importVo);
    }
}
