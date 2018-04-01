package com.khh;

import com.khh.web.service._interface.UserService;
import com.khh.wechat.util.WeiXinUtil;
import com.khh.wechat.vo.MassMessageVO;
import com.khh.wechat.vo.TextVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/4/1.
 */
@ContextConfiguration(locations = { "classpath:spring/spring.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSomething {


    @Autowired
    public UserService userService;


    @Test
    public void test1() throws Exception{
        userService.sendSharesMessageToAllUser();
    }

    @Test
    public void test2() throws Exception{
        MassMessageVO vo = new MassMessageVO();
        vo.setMsgtype("text");

        TextVO textVO = new TextVO("sdfasdfasdf");

        vo.setText(textVO);
        List<String> ids = new ArrayList<>();
        ids.add("oFdoywuv01RQmSbpwLZFL39Lk7zA");
        ids.add("oFdoywilkMSxfqcL7Z6y4ahqLxO0");
        vo.setTouser(ids);

        WeiXinUtil.sendRequestToEveryOne(vo);
    }

}
