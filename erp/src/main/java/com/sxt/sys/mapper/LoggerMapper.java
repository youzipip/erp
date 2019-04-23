package com.sxt.sys.mapper;

import com.sxt.sys.domain.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logger record);

    int insertSelective(Logger record);

    Logger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logger record);

    int updateByPrimaryKey(Logger record);

    //查询所有日志
    List<Logger> queryAllLogger(Logger logger);


}