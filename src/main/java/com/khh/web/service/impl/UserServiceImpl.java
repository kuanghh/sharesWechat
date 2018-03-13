package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.web.dao.UserMapper;
import com.khh.web.domain.User;
import com.khh.web.service._interface.UserService;
import com.khh.web.vo.UserRegisterVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserMapper userMapper;

    @Resource(name = "userMapper")
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
        this.setBaseDao(userMapper);
    }

    @Override
    public User findByOpenId(String openId) throws Exception {
        if (openId == null) return null;
        return userMapper.findByOpenId(openId);
    }

    public int findForCheckExistInfo(UserRegisterVO vo) throws Exception{
        return userMapper.findForCheckExistInfo(vo);
    }
}
