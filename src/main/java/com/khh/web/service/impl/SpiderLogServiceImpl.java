package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.TbSpiderLogMapper;
import com.khh.web.domain.TbSpiderLog;
import com.khh.web.service._interface.SpiderLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
@Service("spiderLogService")
public class SpiderLogServiceImpl extends BaseServiceImpl<TbSpiderLog> implements SpiderLogService{

    private TbSpiderLogMapper tbSpiderLogMapper;

    @Resource(name = "tbSpiderLogMapper")
    public void setTbSpiderLogMapper(TbSpiderLogMapper tbSpiderLogMapper) {
        this.tbSpiderLogMapper = tbSpiderLogMapper;
        this.setBaseDao(tbSpiderLogMapper);
    }


    @Override
    public TbSpiderLog findByTime() throws Exception {
        //获取当天日期
        String today = DateUtil.dateToString(new Date(), "yyyy-MM-dd");

        return tbSpiderLogMapper.findByTime(today);
    }
}
