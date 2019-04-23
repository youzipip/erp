package com.sxt.bus.service;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.sys.utils.DataGridView;

import java.util.List;

public interface GoodsService {

    //查询所有商品
    DataGridView loadAllGoods(GoodsVo goodsVo);

    List<Goods> loadProviderNameJson(GoodsVo goodsVo);
    //修改商品
    void updateGoods(Goods goods);
    //添加商品
    void addGoods(GoodsVo goodsVo);
    //根据供应商id查询商品名称
    List<Goods> loadGoodsByProviderId(Integer id);
    //根据商品id查询商品
    Goods queryGoodsById(Integer goodsid);
}
