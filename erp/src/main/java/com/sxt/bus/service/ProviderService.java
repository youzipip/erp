package com.sxt.bus.service;

import com.sxt.bus.domain.Provider;
import com.sxt.bus.vo.ProviderVo;

import java.util.List;

public interface ProviderService {

    List<Provider> queryAllProvider(ProviderVo providerVo);

}
