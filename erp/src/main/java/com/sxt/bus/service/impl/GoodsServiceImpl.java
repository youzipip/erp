package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Goods;
import com.sxt.bus.mapper.GoodsMapper;
import com.sxt.bus.service.GoodsService;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public DataGridView loadAllGoods(GoodsVo goodsVo) {
        Page<Object> page = PageHelper.startPage(goodsVo.getPage(), goodsVo.getLimit());
        List<Goods> goods = this.goodsMapper.queryAllGoods(goodsVo);
        return new DataGridView(page.getTotal(),goods);
    }

    @Override
    public List<Goods> loadProviderNameJson(GoodsVo goodsVo) {
        return this.goodsMapper.queryAllGoods(goodsVo);
    }

    @Override
    public void updateGoods(Goods goods) {
        this.goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public void addGoods(GoodsVo goodsVo) {
        this.goodsMapper.insertSelective(goodsVo);
    }

    @Override
    public List<Goods> loadGoodsByProviderId(Integer id) {
        return this.goodsMapper.queryGoodsByProviderId(id);
    }

    @Override
    public Goods queryGoodsById(Integer goodsid) {
        return this.goodsMapper.selectByPrimaryKey(goodsid);
    }
}
