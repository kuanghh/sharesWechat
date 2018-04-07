package com.khh.web.service._interface;

import com.khh.base.service.BaseService;
import com.khh.web.domain.TbShares;
import com.khh.web.domain.User;
import com.khh.web.vo.SharesDetailVO;
import com.khh.web.vo.SharesVO;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
public interface SharesService extends BaseService<TbShares>{

    /**
     * 根据股票代码查询
     * @param sharesNum
     * @return
     * @throws Exception
     */
    TbShares findBySharesNum(String sharesNum) throws Exception;

    /**
     * 获取某些指标top5的股票
     * @return
     */
    List<SharesVO> findTop5SharesByField(String field) throws Exception;

    /**
     * 查询某只股票在指定时间内的数据
     * @param user
     * @param sharesNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<SharesDetailVO> findDetailInTimeZone(User user,String sharesNum, String startTime, String endTime) throws Exception;
}
