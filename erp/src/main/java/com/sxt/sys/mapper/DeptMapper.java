package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.Dept;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
    /**
     * 查询部门
     */
   List<Dept> queryAllDept(Dept dept);

    /**
     *查询最大排序码
     */
    Integer queryMaxOrderNum();

    /**
     *查询子节点
     *  */
    Integer checkDeptChildrenNum(Integer id);
}