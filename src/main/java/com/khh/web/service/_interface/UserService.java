package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.User;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 *
 */
public interface UserService extends BaseService<User>{

    /**
     * 根据openId查询
     * @param openId
     * @return
     * @throws Exception
     */
    User findByOpenId(String openId) throws Exception;
}
