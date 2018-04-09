package com.khh.websocket.controller;

import com.khh.base.bean.ResponseBean;
import com.khh.base.controller.BaseController;
import com.khh.web.domain.TbShares;
import com.khh.web.domain.User;
import com.khh.web.service._interface.SharesService;
import com.khh.web.service._interface.UserService;
import com.khh.websocket.vo.ShareMessageVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by 951087952@qq.com on 2018/4/9.
 */
@Controller
public class StompController extends BaseController{


    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "sharesService")
    private SharesService sharesService;


    /**
     *
     * 这个方法表示服务端可以接收客户端通过主题“/app/shares”发送过来的消息
     * ，客户端需要在主题"/topic/shares"上订阅并接收服务端发回的消息
     * (也就是需要客户在js中订阅/topic/shares
     *      stompClient.subscribe('/topic/shares', function(message){
     *           alert(message);
     *           // message就是服务端返回过来的消息
     *           var json = JSON.parse(message.body);
     *      });
     * )
     */
    //这里访问的时候应该用/app/shares
    @MessageMapping("/shares")
    @SendTo("/topic/shares")//发送消息给 订阅了"/topic/shares" 的客户,如果不加@SendTo，默认返回给 "/topic" + @MessageMapping里的路径
    public ResponseBean handlerShout2(ShareMessageVO messageVO) throws Exception{
        ResponseBean response = new ResponseBean();
        String openId = messageVO.getOpenId();

        if(openId == null){
            response.setErrorResponse("无openId信息");
            return response;
        }

        User user = userService.findByOpenId(openId);
        if(user == null){
            response.setErrorResponse("当前用户尚未注册，请注册后再使用");
            return response;
        }

        TbShares shares = sharesService.findBySharesNum(messageVO.getSharesNum());
        if(shares == null){
            response.setErrorResponse("没有当前股票数据信息,目前系统只支持深圳A股..");
            return response;
        }

        StringBuilder builder = new StringBuilder();

        builder.append("用户为'").append(user.getName()).append("'").append(",分享的股票是 :").append(messageVO.getSharesNum()).append("  ").append(shares.getSharesName());
        builder.append("\n").append("分享心得:").append("'").append(messageVO.getSharesDesc()).append("'");

        response.setData("text",builder.toString());

        response.setSuccessResponse("查询成功");
        return response;
    }

}
