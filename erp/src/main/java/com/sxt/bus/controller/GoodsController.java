package com.sxt.bus.controller;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.domain.Provider;
import com.sxt.bus.service.GoodsService;
import com.sxt.bus.service.ProviderService;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.bus.vo.ProviderVo;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;

    private static final String MODEL="goods/";

    /**
     *查询所有商品
     */
    @RequestMapping(MODEL+"loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        return this.goodsService.loadAllGoods(goodsVo);
    }

    /**
     * 初始化供应商下拉列表
     */
    @RequestMapping(MODEL+"loadProviderNameJson")
    public List<Provider> loadProviderNameJson(ProviderVo providerVo){
        return this.providerService.queryAllProvider(providerVo);
    }

    /**
     * 修改商品
     */
    @RequestMapping(MODEL+"updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            this.goodsService.updateGoods(goodsVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }

    /**
     * 添加商品
     */
    @RequestMapping(MODEL+"addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            this.goodsService.addGoods(goodsVo);
            return ResultObj.addOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.addError();
        }
    }

    /**
     * 根据供应商id查询商品名称
     */
    @RequestMapping(MODEL+"loadGoodsByProviderId")
    public List<Goods> loadGoodsByProviderId(Integer id){
        return this.goodsService.loadGoodsByProviderId(id);
    }

}
