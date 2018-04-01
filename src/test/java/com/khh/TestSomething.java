package com.khh;

import com.khh.web.service._interface.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
