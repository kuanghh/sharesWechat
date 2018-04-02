package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.TbUserAppointSpiderLog;

/**
 * Created by 951087952@qq.com on 2018/4/2.
 */
public interface UserAppointSpiderLogService extends BaseService<TbUserAppointSpiderLog>{

    /**
     * 根据msgId查询
     * @param msgId
     * @return
     * @throws Exception
     */
    TbUserAppointSpiderLog findByMsgId(String msgId) throws Exception;
}
