package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbShares;
import org.apache.ibatis.annotations.Param;

public interface TbSharesMapper extends BaseDao<TbShares> {
    int deleteByPrimaryKey(String id);

    int insert(TbShares record);

    int insertSelective(TbShares record);

    TbShares selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbShares record);

    int updateByPrimaryKey(TbShares record);

    /**
     * 根据股票代码查询
     * @param sharesNum
     * @return
     * @throws Exception
     */
    TbShares findBySharesNum(@Param("sharesNum") String sharesNum) throws Exception;
}