package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.TbSharesDetailedMapper;
import com.khh.web.dao.TbSharesMapper;
import com.khh.web.dao.TbUserSearchLogMapper;
import com.khh.web.domain.TbShares;
import com.khh.web.domain.TbUserSearchLog;
import com.khh.web.domain.User;
import com.khh.web.service._interface.SharesService;
import com.khh.web.vo.SharesDetailVO;
import com.khh.web.vo.SharesVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
@Service("sharesService")
public class SharesServiceImpl extends BaseServiceImpl<TbShares> implements SharesService{

    private TbSharesMapper tbSharesMapper;

    private TbSharesDetailedMapper tbSharesDetailedMapper;

    private TbUserSearchLogMapper tbUserSearchLogMapper;

    @Resource(name = "tbSharesMapper")
    public void setTbSharesMapper(TbSharesMapper tbSharesMapper){
        this.tbSharesMapper = tbSharesMapper;
        this.setBaseDao(tbSharesMapper);
    }

    @Resource(name = "tbSharesDetailedMapper")
    public void setTbSharesDetailedMapper(TbSharesDetailedMapper tbSharesDetailedMapper) {
        this.tbSharesDetailedMapper = tbSharesDetailedMapper;
    }

    @Resource(name = "tbUserSearchLogMapper")
    public void setTbUserSearchLogMapper(TbUserSearchLogMapper tbUserSearchLogMapper) {
        this.tbUserSearchLogMapper = tbUserSearchLogMapper;
    }

    @Override
    public TbShares findBySharesNum(String sharesNum) throws Exception {
        if(sharesNum == null || sharesNum.trim().length() == 1) return null;

        return tbSharesMapper.findBySharesNum(sharesNum);
    }

    @Override
    public List<SharesVO> findTop5SharesByField(String field) throws Exception {
        String yesterday = DateUtil.getYesterday(DateUtil.DATE_PATTERN_DAY);
        return tbSharesDetailedMapper.findTopSharesByKey(field,yesterday,5);
    }

    @Override
    public List<SharesDetailVO> findDetailInTimeZone(User user, String sharesNum, String startTime, String endTime) throws Exception {
        //第一步查询结果
        List<SharesDetailVO> listVO = tbSharesDetailedMapper.findDetailInTimeZone(sharesNum, startTime, endTime);

        if(listVO == null || listVO.isEmpty()){
            return null;
        }

        //第二步插入记录
        TbUserSearchLog userLog = new TbUserSearchLog();
        userLog.setId(UUID.randomUUID().toString());
        userLog.setCreateTime(new Date());
        userLog.setOpenId(user.getOpenId());
        userLog.setUserId(user.getId());
        userLog.setStartTime(DateUtil.stringToDate(startTime));
        userLog.setEndTime(DateUtil.stringToDate(endTime));
        userLog.setSharesId(listVO.get(0).getSharesId());

        tbUserSearchLogMapper.insert(userLog);
        return listVO;
    }


}
