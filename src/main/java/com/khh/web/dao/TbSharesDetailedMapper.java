package com.khh.web.dao;

import com.khh.base.dao.BaseDao;
import com.khh.web.domain.TbSharesDetailed;
import com.khh.web.vo.SharesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSharesDetailedMapper extends BaseDao<TbSharesDetailed> {
    int deleteByPrimaryKey(String id);

    int insert(TbSharesDetailed record);

    int insertSelective(TbSharesDetailed record);

    TbSharesDetailed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSharesDetailed record);

    int updateByPrimaryKey(TbSharesDetailed record);


    /**
     * 根据key去获取排名前num的股票
     * @param key
     * @return
     */
    List<SharesVO> findTopSharesByKey(@Param("key") String key, @Param("today") String today, @Param("num") Integer num);
}