package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.web.dao.TbSharesMapper;
import com.khh.web.domain.TbShares;
import com.khh.web.service._interface.SharesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
@Service("sharesService")
public class SharesServiceImpl extends BaseServiceImpl<TbShares> implements SharesService{

    private TbSharesMapper tbSharesMapper;

    @Resource(name = "tbSharesMapper")
    public void setUserMapper(TbSharesMapper tbSharesMapper){
        this.tbSharesMapper = tbSharesMapper;
        this.setBaseDao(tbSharesMapper);
    }

}
