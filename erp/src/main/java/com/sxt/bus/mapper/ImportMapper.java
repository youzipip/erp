package com.sxt.bus.mapper;

import com.sxt.bus.domain.Import;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface ImportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Import record);

    int insertSelective(Import record);

    Import selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Import record);

    int updateByPrimaryKey(Import record);
    //查询所有进货信息
    List<Import> queryAllImport(Import imports);
}