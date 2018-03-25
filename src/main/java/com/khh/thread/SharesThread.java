package com.khh.thread;

import com.khh.rpc.SharesRPCClient;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 951087952@qq.com on 2018/3/25.
 * 调用python系统的线程
 */
public class SharesThread implements Runnable{



    @Override
    public void run() {
        SharesRPCClient client = null;
        try {
            client = new SharesRPCClient();
            client.run("30");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
