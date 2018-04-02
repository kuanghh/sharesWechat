package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.web.dao.TbUserAppointSpiderLogMapper;
import com.khh.web.domain.TbUserAppointSpiderLog;
import com.khh.web.service._interface.UserAppointSpiderLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/4/2.
 */
@Service("userAppointSpiderLogService")
public class UserAppointSpiderLogServiceImpl extends BaseServiceImpl<TbUserAppointSpiderLog> implements UserAppointSpiderLogService {

    private TbUserAppointSpiderLogMapper tbUserAppointSpiderLogMapper;

    @Resource(name = "tbUserAppointSpiderLogMapper")
    public void setTbUserAppointSpiderLogMapper(TbUserAppointSpiderLogMapper tbUserAppointSpiderLogMapper) {
        this.tbUserAppointSpiderLogMapper = tbUserAppointSpiderLogMapper;
        setBaseDao(tbUserAppointSpiderLogMapper);
    }

    @Override
    public TbUserAppointSpiderLog findByMsgId(String msgId) throws Exception {
        return tbUserAppointSpiderLogMapper.findByMsgId(msgId);
    }
}
