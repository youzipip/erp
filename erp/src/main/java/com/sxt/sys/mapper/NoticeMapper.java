package com.sxt.sys.mapper;

import com.sxt.sys.domain.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    /**
     * 查询所有公告
     */
    List<Notice> queryAllNotice(Notice notice);
}