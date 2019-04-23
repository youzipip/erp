package com.sxt.bus.service;

import com.sxt.bus.vo.ImportVo;
import com.sxt.sys.utils.DataGridView;

public interface ImportService {

    DataGridView loadAllImport(ImportVo importVo);
    //商品进货
    void addImport(ImportVo importVo);
    //修改进货信息
    void updateImport(ImportVo importVo);
}
