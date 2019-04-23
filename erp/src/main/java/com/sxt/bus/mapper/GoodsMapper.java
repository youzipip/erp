package com.sxt.bus.mapper;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.vo.GoodsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    //查询所有商品
    List<Goods> queryAllGoods(Goods goods);
    //根据供应商id查询商品名称
    List<Goods> queryGoodsByProviderId(Integer id);
}