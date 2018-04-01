package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.TbSpiderLog;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
public interface SpiderLogService extends BaseService<TbSpiderLog> {

    /**
     * 获取今天的定时爬虫log日志
     * @return
     * @throws Exception
     */
    TbSpiderLog findByTime() throws Exception;

}
