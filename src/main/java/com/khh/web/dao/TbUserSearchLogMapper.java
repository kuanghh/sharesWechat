package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbUserSearchLog;

public interface TbUserSearchLogMapper extends BaseDao<TbUserSearchLog> {
    int deleteByPrimaryKey(String id);

    int insert(TbUserSearchLog record);

    int insertSelective(TbUserSearchLog record);

    TbUserSearchLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbUserSearchLog record);

    int updateByPrimaryKey(TbUserSearchLog record);
}