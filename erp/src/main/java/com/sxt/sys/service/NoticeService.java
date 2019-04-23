package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NoticeVo;

public interface NoticeService {

    /**
     * 查询所有公告
     */
    DataGridView loadAllNotice(NoticeVo noticeVo);

    void addNotice(NoticeVo noticeVo);

    void updateNotice(NoticeVo noticeVo);

    void deleteNotice(Integer id);
}
