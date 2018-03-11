package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseDao<User>{
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据openId查找用户
     * @param openId
     * @return
     */
    User findByOpenId(@Param("openId") String openId);
}