package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.User;
import com.khh.web.vo.UserRegisterVO;

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


    /**
     * 检测是否信息重复问题
     * @param vo
     * @return
     * @throws Exception
     */
    int findForCheckExistInfo(UserRegisterVO vo) throws Exception;


    /**
     * 每天爬虫获取数据后,推送消息给用户
     * @throws Exception
     */
    void sendSharesMessageToAllUser() throws Exception;
}
