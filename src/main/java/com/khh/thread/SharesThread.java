package com.khh.thread;

import com.khh.rpc.SharesRPCClient;
import com.khh.web.util.SharesUtil;

/**
 * Created by 951087952@qq.com on 2018/3/25.
 * 调用python系统定时爬虫的线程
 */
public class SharesThread implements Runnable{

    @Override
    public void run() {

        try {
            SharesUtil.runRegularSpider();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
