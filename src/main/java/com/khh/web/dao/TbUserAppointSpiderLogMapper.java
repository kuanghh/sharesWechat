package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbUserAppointSpiderLog;
import org.apache.ibatis.annotations.Param;

public interface TbUserAppointSpiderLogMapper extends BaseDao<TbUserAppointSpiderLog> {
    int deleteByPrimaryKey(String id);

    int insert(TbUserAppointSpiderLog record);

    int insertSelective(TbUserAppointSpiderLog record);

    TbUserAppointSpiderLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbUserAppointSpiderLog record);

    int updateByPrimaryKey(TbUserAppointSpiderLog record);


    /**
     * 根据msgId查询
     * @param msgId
     * @return
     * @throws Exception
     */
    TbUserAppointSpiderLog findByMsgId(@Param("msgId") String msgId) throws Exception;
}