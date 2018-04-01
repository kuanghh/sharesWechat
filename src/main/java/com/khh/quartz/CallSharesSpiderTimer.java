package com.khh.quartz;

import com.khh.thread.SharesThread;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 951087952@qq.com on 2018/3/19.
 * 唤醒爬虫定时器
 */
@Component("allSharesSpiderTimer")
public class CallSharesSpiderTimer {

    private static int i = 1;

    @Scheduled(cron = "5 * * ? * *")
    public void callsharesSpider() throws Exception{
        if(i == 1){
            System.out.println("开始跑线程啦");
            Thread thread = new Thread(new SharesThread());
            thread.start();
            i++;
        }

    }



    //匹配的格式分别是 秒 分 时 日 月 周几
    // 0/20 * * * * ? ---->  每20秒运行一次
    // 15 0/2 * * * ? ---->  每两分钟跑一次（每分钟的第15秒，触发）
    // 0 0/2 8-17 * * ? ----> 每天早上的8点到下午的5点，每两分钟运行一次
    // 0 0/1 14 1,20 * ? ----> 每个月的1-20号的每天下午2点，每隔1分钟调用一次
    // 0 0-5 14 * * ?  ---->   每天下午的 2点到2点05分每分触发
    // 0 10,44 14 ? 3 WED        3月分每周三下午的 2点10分和2点44分触发
//    @Scheduled(cron = "0/5 * * ? * *")
//    public void test() throws Exception{
//        System.out.println("这是一个任务调度器,每五秒执行一次");
//    }

}
