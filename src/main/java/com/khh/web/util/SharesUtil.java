package com.khh.web.util;

import com.khh.base.util.DateUtil;
import com.khh.rpc.RPCUtil;
import com.khh.rpc.SharesRPCClient;
import com.khh.web.domain.TbSharesDetailed;
import com.khh.web.domain.TbSpiderLog;
import com.khh.web.enm.SharesParamEnum;
import com.khh.web.service._interface.SpiderLogService;
import com.khh.web.service._interface.UserService;
import com.khh.web.vo.SharesInterface;
import com.khh.web.vo.SharesVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2018/3/31.
 */
public class SharesUtil {

    private static Logger log = LoggerFactory.getLogger(SharesUtil.class);

    private static SpiderLogService spiderLogService;
    private static UserService userService;
    private static ApplicationContext ac;

    static{
        try {
            ac = new ClassPathXmlApplicationContext("spring/spring.xml");
            spiderLogService = (SpiderLogService) ac.getBean("spiderLogService");
            userService = (UserService) ac.getBean("userService");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 调用定时爬虫
     * @throws Exception
     */
    public static void runRegularSpider() throws Exception{
        SharesRPCClient client = new SharesRPCClient();
        String response = "";
        try{

            response = client.call(RPCUtil.REGULAR_QUEUE_NAME, RPCUtil.REGULAR_COMMAND);
            System.out.println(" regular response received :" + response);
            //看否是否调用成功
            if(RPCUtil.RPC_RESPONSE_STATE_SUCCESS.equals(response)) {//成功,调用群发接口，进行推送消息


                //循环查看当天数据是否获取成功
                TbSpiderLog spiderLog = null;
                //检查频率为6分钟一次
                Long findFrequency = 6L * 1000 * 60;

                //总检查时间90分钟
                Long allTime = 90L * 1000 * 60;

                while(true){

                    spiderLog= spiderLogService.findByTime();
                    if(spiderLog != null){
                        break;
                    }
                    Thread.sleep(findFrequency); //每6分钟检查一次
                    allTime -= findFrequency;
                    if(allTime < 0){
                        break;
                    }
                }

                if(spiderLog == null || spiderLog.getState() == TbSpiderLog.STATE_FAILED) return;


                //调用群发接口
                userService.sendSharesMessageToAllUser();
            }else{
                //失败的话,尚未考虑
                log.error(DateUtil.getToday() + "定时爬虫调用失败");
                return;
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.debug("今天的定期爬虫任务结束...");
        }
    }


    /**
     * 将股票topN转换成固定文字
     * @param list
     * @param key 关键字
     * @return
     */
    public static String getTopString(List<SharesVO> list, SharesParamEnum key) throws Exception{


        StringBuilder builder = new StringBuilder();

        builder.append("今天<a href='javascript:void(0);'>")
                .append(key.getDesc())
                .append("</a>top10的股票有: \n");

        for (int i = 0; i < list.size(); i++) {
            SharesVO sharesVO = list.get(i);
            builder.append(i).append(". ")
                    .append("<a href='").append(sharesVO.getSharesHref()).append("'>")
                    .append(sharesVO.getSharesNum())
                    .append("</a>").append("  ")
                    .append(sharesVO.getSharesName()).append("  ")
                    .append(key.getDesc()).append("为: ")
                    .append(getSharesValue(sharesVO, key.getField())).append("\n");

        }


        return builder.toString();
    }



    public static  String getSharesValue(SharesInterface shares , String key){

        String message = "";

        if(SharesParamEnum.open_price.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getOpenPrice())));

        }else if(SharesParamEnum.close_price.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getClosePrice())));

        }else if(SharesParamEnum.ceilling_price.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getCeillingPrice())));

        }else if(SharesParamEnum.floor_price.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getFloorPrice())));

        }else if(SharesParamEnum.rise_and_fall_range.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getRiseAndFallRange()))) + "%";

        }else if(SharesParamEnum.rise_and_fall_quota.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getRiseAndFallQuota()))) ;

        }else if(SharesParamEnum.volume.getField().equals(key)){
            message = String.valueOf(shares.getVolume()) + "手";

        }else if(SharesParamEnum.turn_volume.getField().equals(key)){
            message = String.valueOf(shares.getTurnVolume()) + "万";

        }else if(SharesParamEnum.turnover_rate.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getTurnoverRate()))) + "%";

        }else if(SharesParamEnum.amplitude.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getAmplitude()))) + "%";

        }else if(SharesParamEnum.p_e_ratio.getField().equals(key)){
            message = String.valueOf(MoneyConvert.moneyLongToStr(Long.valueOf(shares.getpERatio()))) + "(MRQ)";

        }
        return message;
    }
}
