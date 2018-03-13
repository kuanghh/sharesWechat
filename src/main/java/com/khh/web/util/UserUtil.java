package com.khh.web.util;

import com.khh.web.domain.User;
import com.khh.web.vo.UserRegisterVO;

import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2018/3/13.
 *
 */
public class UserUtil {

    public static Integer USER_STATE_SUBSCRIBE = 1;  //已关注
    public static Integer USER_STATE_UNSUBSCRIBE = 0;//未关注

    public static Integer USER_BINGDING_REGISTER = 1;   //已注册
    public static Integer USER_BINGDING_UNREGISTER = 0; //未注册

    public static User assembleUserPO(UserRegisterVO vo) throws Exception{
        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setAccount(vo.getAccount());
        user.setEmail(vo.getEmail());
        user.setName(vo.getName());
        user.setOpenId(vo.getOpenId());
        user.setPhone(vo.getPhone());

        // todo set password
        user.setIsBinding(USER_BINGDING_REGISTER);
        user.setStatus(USER_STATE_SUBSCRIBE);
        user.setRegisterTime(new Date());
        return user;
    }

}
