package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbShares;

public interface TbSharesMapper extends BaseDao<TbShares> {
    int deleteByPrimaryKey(String id);

    int insert(TbShares record);

    int insertSelective(TbShares record);

    TbShares selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbShares record);

    int updateByPrimaryKey(TbShares record);
}