package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbUserAppointSpiderLog;

public interface TbUserAppointSpiderLogMapper extends BaseDao<TbUserAppointSpiderLog> {
    int deleteByPrimaryKey(String id);

    int insert(TbUserAppointSpiderLog record);

    int insertSelective(TbUserAppointSpiderLog record);

    TbUserAppointSpiderLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbUserAppointSpiderLog record);

    int updateByPrimaryKey(TbUserAppointSpiderLog record);
}