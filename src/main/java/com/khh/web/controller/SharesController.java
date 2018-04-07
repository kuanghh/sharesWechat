package com.khh.web.controller;

import com.khh.base.bean.ResponseBean;
import com.khh.base.controller.BaseController;
import com.khh.base.util.DateUtil;
import com.khh.web.domain.User;
import com.khh.web.service._interface.SharesService;
import com.khh.web.service._interface.UserService;
import com.khh.web.vo.SharesDetailVO;
import com.khh.web.vo.SharesVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/4/7.
 * 股票Controller
 */
@Controller
@RequestMapping(value = "/shares")
public class SharesController extends BaseController{

    @Resource(name = "userService")
    private UserService userService;


    @Resource(name = "sharesService")
    private SharesService sharesService;



    @ResponseBody
    @RequestMapping(value = "/findInTimeZone", method = RequestMethod.POST)
    public ResponseBean findSharesDetailInTimeZone(@RequestParam("sharesNum") String sharesNum,
                                                   @RequestParam("startTime")String startTime,
                                                   @RequestParam("endTime")String endTime,
                                                   @RequestParam("openId")String openId) throws Exception{
        ResponseBean response = new ResponseBean();

        if(StringUtils.isEmpty(sharesNum)){
            response.setErrorResponse("股票代码不能为空");
            return response;
        }

        if(!DateUtil.isDate(startTime) || !DateUtil.isDate(endTime)){
            response.setErrorResponse("输入时间格式不正确");
            return response;
        }

        if(DateUtil.stringToDate(startTime,DateUtil.DATE_PATTERN_DAY).after(DateUtil.stringToDate(endTime,DateUtil.DATE_PATTERN_DAY))){
            response.setErrorResponse("开始时间不能在结束时间之后");
            return response;
        }

        if(StringUtils.isEmpty(openId)){
            response.setErrorResponse("openId不能为空");
            return response;
        }
        User user = userService.findByOpenId(openId);
        if(user == null){
            response.setErrorResponse("当前用户尚未注册，请注册后再使用");
            return response;
        }

        List<SharesDetailVO> sharesVOList = sharesService.findDetailInTimeZone(user ,sharesNum, startTime, endTime);
        if(sharesVOList == null || sharesVOList.isEmpty()){
            response.setErrorResponse("没有当前股票数据信息,目前系统只支持深圳A股..");
            return response;
        }

        response.setData("list",sharesVOList);
        response.setSuccessResponse("查询成功");

        return response;
    }
}
