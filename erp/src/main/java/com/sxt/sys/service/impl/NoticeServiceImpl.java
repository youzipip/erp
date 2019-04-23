package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Notice;
import com.sxt.sys.mapper.NoticeMapper;
import com.sxt.sys.service.NoticeService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    @Transactional(readOnly = true)
    public DataGridView loadAllNotice(NoticeVo noticeVo) {
        Page<Object> page = PageHelper.startPage(noticeVo.getPage(), noticeVo.getLimit());
        List<Notice> notices = this.noticeMapper.queryAllNotice(noticeVo);
        return new DataGridView(page.getTotal(),notices);
    }

    @Override
    public void addNotice(NoticeVo noticeVo) {
        this.noticeMapper.insertSelective(noticeVo);
    }

    @Override
    public void updateNotice(NoticeVo noticeVo) {
        this.noticeMapper.updateByPrimaryKeySelective(noticeVo);
    }

    @Override
    public void deleteNotice(Integer id) {
        this.noticeMapper.deleteByPrimaryKey(id);
    }
}
