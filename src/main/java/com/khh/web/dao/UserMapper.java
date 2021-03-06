package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.User;
import com.khh.web.vo.UserRegisterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 检测是否信息重复问题
     * @param vo
     * @return
     * @throws Exception
     */
    int findForCheckExistInfo(@Param("userRegisterVO")UserRegisterVO vo) throws Exception;


    /**
     * 获取所有注册过的用户openId
     * @return
     * @throws Exception
     */
    List<String> findAllValidOpenId() throws Exception;


}