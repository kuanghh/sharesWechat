package com.khh.web.util;

import com.alibaba.fastjson.JSONObject;
import com.khh.base.util.DateUtil;
import com.khh.rpc.RPCUtil;
import com.khh.rpc.SharesRPCClient;
import com.khh.web.domain.TbSharesDetailed;
import com.khh.web.domain.TbSpiderLog;
import com.khh.web.domain.TbUserAppointSpiderLog;
import com.khh.web.enm.SharesParamEnum;
import com.khh.web.service._interface.SpiderLogService;
import com.khh.web.service._interface.UserAppointSpiderLogService;
import com.khh.web.service._interface.UserService;
import com.khh.web.vo.CallBackSharesVO;
import com.khh.web.vo.CallSharesVO;
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
    private static UserAppointSpiderLogService userAppointSpiderLogService;
    private static ApplicationContext ac;

    static{
        try {
            ac = new ClassPathXmlApplicationContext("spring/spring.xml");
            spiderLogService = (SpiderLogService) ac.getBean("spiderLogService");
            userService = (UserService) ac.getBean("userService");
            userAppointSpiderLogService = (UserAppointSpiderLogService) ac.getBean("userAppointSpiderLogService");

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
                    System.out.println("检查一次..");
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
            client.close();
        }
    }


    /**
     * 调用实时爬虫
     * @param vo
     * @throws Exception
     */
    public static String runAppointSpider(CallSharesVO vo, String shareName) throws Exception{
        SharesRPCClient client = new SharesRPCClient();

        String jsonData = JSONObject.toJSONString(vo);
        String response = client.call(RPCUtil.RPC_QUEUE_NAME, jsonData);

        String responseJson = vo.getShares_num() + "  " + shareName + ": \n";
        if(RPCUtil.RPC_RESPONSE_STATE_SUCCESS.equals(response)){//获取数据成功
            //由于是实时爬虫,所以python爬虫也没有调用新的线程去调用爬虫，所以是按顺序执行的
            TbUserAppointSpiderLog po = userAppointSpiderLogService.findByMsgId(vo.getMsg_id());
            if(po == null || po.getState() == SharesCommonUtil.APPOINT_SPIDER_LOG_STATE_FAILD){//调用失败
                log.error("调用爬虫失败,失败msgId：" + vo.getMsg_id());
                return null;
            }else{

                String dataJson = po.getDataJson();
                CallBackSharesVO callBackSharesVO = JSONObject.parseObject(dataJson, CallBackSharesVO.class);
                responseJson += callBackSharesVO.toString();
                log.info("调用实时爬虫成功");
            }
        }
        client.close();
        return responseJson;
    }

    /**
     * 将股票topN转换成固定文字
     * @param list
     * @param key 关键字
     * @return
     */
    public static String getTopString(boolean isToday ,List<SharesVO> list, SharesParamEnum key) throws Exception{


        StringBuilder builder = new StringBuilder(isToday ? "今天":"昨日");

        builder.append("<a href='javascript:void(0);'>")
                .append(key.getDesc())
                .append("</a>top").append(list.size()).append("的股票有: \n");

        for (int i = 0; i < list.size(); i++) {
            SharesVO sharesVO = list.get(i);
            builder.append("(").append(i + 1).append(")").append(". ")
                    .append("<a href='").append(sharesVO.getSharesHref()).append("'>")
                    .append(sharesVO.getSharesNum())
                    .append("</a>").append("  ")
                    .append(sharesVO.getSharesName()).append("\n")
                    .append("      ").append(key.getDesc()).append("为: ")
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
