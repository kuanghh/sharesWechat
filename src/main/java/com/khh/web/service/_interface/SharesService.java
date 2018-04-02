package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.TbShares;
import com.khh.web.domain.User;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
public interface SharesService extends BaseService<TbShares>{

    /**
     * 根据股票代码查询
     * @param sharesNum
     * @return
     * @throws Exception
     */
    TbShares findBySharesNum(String sharesNum) throws Exception;
}