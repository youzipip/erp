package com.sxt.bus.controller;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.domain.Provider;
import com.sxt.bus.service.GoodsService;
import com.sxt.bus.service.ImportService;
import com.sxt.bus.service.ProviderService;
import com.sxt.bus.utils.TreeNode;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.bus.vo.ImportVo;
import com.sxt.bus.vo.ProviderVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ImportController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private ImportService importService;
    @Autowired
    private GoodsService goodsService;

    private static final String MODEL="import/";

    /**
     * 加载商品进货页面左侧json树
     */
    @RequestMapping(MODEL+"loadImportManagerLeftTreeJson")
    public DataGridView loadProviderManagerLeftTreeJson(ProviderVo providerVo){
        providerVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Provider> providers = this.providerService.queryAllProvider(providerVo);
        List<TreeNode> node = new ArrayList<>();
        node.add(new TreeNode(001, "所有供应商",0));
        for (Provider provider : providers) {
            Integer id = provider.getId();
            String providername = provider.getProvidername();
            Integer parentId = 001;
            node.add(new TreeNode(id, providername,parentId));
        }
        return new DataGridView(Long.valueOf(node.size()),node);
    }

    /**
     * 查询所有进货信息
     */
    @RequestMapping(MODEL+"loadAllImport")
    public DataGridView loadAllImport(ImportVo importVo){
       return this.importService.loadAllImport(importVo);
    }

    /**
     * 商品进货
     */
    @RequestMapping(MODEL+"addImport")
    public ResultObj addImport(ImportVo importVo){
        User user = (User)WebUtils.getCurrentSession().getAttribute("user");
        importVo.setInporttime(new Date());
        importVo.setOperateperson(user.getName());
        //根据商品id查询商品
        Goods goods=   goodsService.queryGoodsById(importVo.getGoodsid());
        //判断库存量与进货量
        if(goods.getNumber()>=importVo.getNumber()){
            this.goodsService.updateGoods(goods);
            try {
                this.importService.addImport(importVo);
                return ResultObj.addOk();
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.addError();
            }
        }else{
           return new ResultObj(-1,"");
        }
    }
    /**
     * 修改进货商品
     */
    @RequestMapping(MODEL+"updateImport")
    public ResultObj updateImport(ImportVo importVo){
        try {
            this.importService.updateImport(importVo);
            return ResultObj.updateOk();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.updateError();
        }
    }
}
