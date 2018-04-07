package com.khh.web.service.impl;

import com.khh.base.service.impl.BaseServiceImpl;
import com.khh.base.util.DateUtil;
import com.khh.web.dao.TbSharesDetailedMapper;
import com.khh.web.dao.UserMapper;
import com.khh.web.domain.TbSharesDetailed;
import com.khh.web.domain.User;
import com.khh.web.enm.SharesParamEnum;
import com.khh.web.service._interface.UserService;
import com.khh.web.util.SharesUtil;
import com.khh.web.vo.SharesVO;
import com.khh.web.vo.UserRegisterVO;
import com.khh.wechat.util.MessageUtil;
import com.khh.wechat.util.WeiXinUtil;
import com.khh.wechat.vo.MassMessageVO;
import com.khh.wechat.vo.TextVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserMapper userMapper;
    private TbSharesDetailedMapper sharesDetailedMapper;

    @Resource(name = "userMapper")
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
        this.setBaseDao(userMapper);
    }

    @Resource(name = "tbSharesDetailedMapper")
    public void setTbSharesDetailedMapper(TbSharesDetailedMapper sharesDetailedMapper) {
        this.sharesDetailedMapper = sharesDetailedMapper;
    }

    @Override
    public User findByOpenId(String openId) throws Exception {
        if (openId == null) return null;
        return userMapper.findByOpenId(openId);
    }

    public int findForCheckExistInfo(UserRegisterVO vo) throws Exception{
        return userMapper.findForCheckExistInfo(vo);
    }

    @Override
    public void sendSharesMessageToAllUser() throws Exception {
        MassMessageVO vo = new MassMessageVO();

        String today = DateUtil.getToday(DateUtil.DATE_PATTERN_DAY);
//        String today = "2018-03-19";

        //将所有注册过的用户的OpenId获取出来
        List<String> opIdList = userMapper.findAllValidOpenId();

        if(opIdList == null || opIdList.size() <= 1) return; //没有用户就不操作了,如果用户只有一个，也会出错

        //获取市盈率top10
        List<SharesVO> pERatioTop = sharesDetailedMapper.findTopSharesByKey(SharesParamEnum.p_e_ratio.getField(), today,5);

        //获取交易量top10
        List<SharesVO> volumeTop = sharesDetailedMapper.findTopSharesByKey(SharesParamEnum.volume.getField(), today,5);

        //获取最高价的top10
        List<SharesVO> priceTop = sharesDetailedMapper.findTopSharesByKey(SharesParamEnum.ceilling_price.getField(), today,5);

        StringBuilder builder = new StringBuilder();

        builder.append(SharesUtil.getTopString(true, pERatioTop, SharesParamEnum.p_e_ratio));
        builder.append("\n");
        builder.append(SharesUtil.getTopString(true, volumeTop, SharesParamEnum.volume));
        builder.append("\n");
        builder.append(SharesUtil.getTopString(true, priceTop, SharesParamEnum.ceilling_price));
        builder.append("\n");

        vo.setMsgtype(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        vo.setTouser(opIdList);
        TextVO textVO = new TextVO(builder.toString());

        vo.setText(textVO);

        System.out.println(builder.toString());
        WeiXinUtil.sendRequestToEveryOne(vo);
    }
}
