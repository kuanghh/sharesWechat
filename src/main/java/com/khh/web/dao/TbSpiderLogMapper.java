package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbSpiderLog;
import org.apache.ibatis.annotations.Param;

public interface TbSpiderLogMapper extends BaseDao<TbSpiderLog> {
    int deleteByPrimaryKey(String id);

    int insert(TbSpiderLog record);

    int insertSelective(TbSpiderLog record);

    TbSpiderLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSpiderLog record);

    int updateByPrimaryKey(TbSpiderLog record);

    TbSpiderLog findByTime(@Param("time") String time);
}