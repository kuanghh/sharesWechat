package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.TbSharesDetailedMapper;
import com.khh.web.dao.TbSharesMapper;
import com.khh.web.domain.TbShares;
import com.khh.web.service._interface.SharesService;
import com.khh.web.vo.SharesVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
@Service("sharesService")
public class SharesServiceImpl extends BaseServiceImpl<TbShares> implements SharesService{

    private TbSharesMapper tbSharesMapper;

    private TbSharesDetailedMapper tbSharesDetailedMapper;

    @Resource(name = "tbSharesMapper")
    public void setTbSharesMapper(TbSharesMapper tbSharesMapper){
        this.tbSharesMapper = tbSharesMapper;
        this.setBaseDao(tbSharesMapper);
    }

    @Resource(name = "tbSharesDetailedMapper")
    public void setTbSharesDetailedMapper(TbSharesDetailedMapper tbSharesDetailedMapper) {
        this.tbSharesDetailedMapper = tbSharesDetailedMapper;
    }

    @Override
    public TbShares findBySharesNum(String sharesNum) throws Exception {
        if(sharesNum == null || sharesNum.trim().length() == 1) return null;

        return tbSharesMapper.findBySharesNum(sharesNum);
    }

    @Override
    public List<SharesVO> findTop5SharesByField(String field) throws Exception {
        String yesterday = DateUtil.getYesterday();
        return tbSharesDetailedMapper.findTopSharesByKey(field,yesterday,5);
    }
}
