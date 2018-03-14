package com.khh.web.controller;

import com.khh.base.bean.ResponseBean;
import com.khh.base.controller.BaseController;
import com.khh.web.domain.User;
import com.khh.web.service._interface.UserService;
import com.khh.web.util.UserUtil;
import com.khh.web.vo.UserRegisterVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 用户controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = "application/json")
    public ResponseBean register(@Valid @RequestBody UserRegisterVO userVO, BindingResult result) throws Exception{
        ResponseBean responseBean = new ResponseBean();

        //信息验证
        if(result.hasErrors()){
            responseBean.setErrorResponse(result.getFieldError().getDefaultMessage());
            return responseBean;
        }

        User user = userService.findByOpenId(userVO.getOpenId());
        if(user == null){
            user = UserUtil.assembleUserPO(userVO);
            userService.insertPO(user,false);
        }else if(user.getIsBinding().equals(UserUtil.USER_BINGDING_UNREGISTER)){ //当前openId还没有注册

            //检测账号，邮箱，电话是否重复
            if(userService.findForCheckExistInfo(userVO) > 0){
                responseBean.setErrorResponse("账号、邮箱或电话信息有重复");
                return responseBean;
            }

            user.setAccount(userVO.getAccount());
            user.setEmail(userVO.getEmail());
            user.setName(userVO.getName());
            user.setPhone(userVO.getPhone());
            //todo password
            user.setRegisterTime(new Date());
            user.setIsBinding(UserUtil.USER_BINGDING_REGISTER);
            user.setStatus(UserUtil.USER_STATE_SUBSCRIBE);
            userService.updatePO(user,false);
        }else{
            responseBean.setErrorResponse("当前微信账号已成功注册");
            return responseBean;
        }

        responseBean.setSuccessResponse("注册成功");
        return responseBean;
    }

    @ResponseBody
    @RequestMapping(value = "/checkInfo", method = RequestMethod.POST)
    public ResponseBean ajaxCheckExistInfo(@RequestParam("key")String key,@RequestParam("value")String value) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        UserRegisterVO vo = new UserRegisterVO();
        String errorMessage = "";
        if("email".equals(key)){
            vo.setEmail(value);
            errorMessage = "邮箱已经有人使用了";
        }else if("phone".equals(key)){
            vo.setPhone(value);
            errorMessage = "电话号码已经有人使用了";
        }else if("account".equals(key)){
            vo.setAccount(value);
            errorMessage = "账号已经有人使用了";
        }

        if(userService.findForCheckExistInfo(vo) > 0){
            responseBean.setErrorResponse(errorMessage);
            return responseBean;
        }
        responseBean.setSuccessResponse("可以使用");
        return responseBean;
    }

}
