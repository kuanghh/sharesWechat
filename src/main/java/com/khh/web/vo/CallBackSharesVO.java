package com.khh.web.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2018/4/3.
 * 调用实时爬虫后返回来的数据vo
 *
 */
public class CallBackSharesVO implements Serializable{


    private String price;                //当前价格
    private String rise_and_fall_quota;  //涨跌幅（%）
    private String rise_and_fall_range;  //涨跌额
    private String height;               //最高价
    private String low;                  //最低价
    private String today_open;           //开盘价
    private String yesterday_close;      //收盘价
    private String volumn;               //成交量(手)
    private String turn_volume;          //成交额（万）
    private String turnover_rate;        //换手率（%）
    private String amplitude;            //振幅
    private String p_e_ratio;            //市盈率

    @Override
    public String toString() {
        return "当前价格 :" + price + "\n" +
                "涨跌幅 :" + rise_and_fall_quota + "\n" +
                "涨跌额 :" + rise_and_fall_range + "\n" +
                "最高价 :" + height + "\n" +
                "最低价 :" + low + "\n" +
                "开盘价 :" + today_open + "\n" +
                "收盘价 :" + yesterday_close + "\n" +
                "成交量 :" + volumn + "\n" +
                "成交额 :" + turn_volume + "\n" +
                "换手率 :" + turnover_rate + "\n" +
                "振  幅 :" + amplitude + "\n" +
                "市盈率(MRQ) :" + p_e_ratio + "\n" ;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRise_and_fall_quota() {
        return rise_and_fall_quota;
    }

    public void setRise_and_fall_quota(String rise_and_fall_quota) {
        this.rise_and_fall_quota = rise_and_fall_quota;
    }

    public String getRise_and_fall_range() {
        return rise_and_fall_range;
    }

    public void setRise_and_fall_range(String rise_and_fall_range) {
        this.rise_and_fall_range = rise_and_fall_range;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getToday_open() {
        return today_open;
    }

    public void setToday_open(String today_open) {
        this.today_open = today_open;
    }

    public String getYesterday_close() {
        return yesterday_close;
    }

    public void setYesterday_close(String yesterday_close) {
        this.yesterday_close = yesterday_close;
    }

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public String getTurn_volume() {
        return turn_volume;
    }

    public void setTurn_volume(String turn_volume) {
        this.turn_volume = turn_volume;
    }

    public String getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(String turnover_rate) {
        this.turnover_rate = turnover_rate;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getP_e_ratio() {
        return p_e_ratio;
    }

    public void setP_e_ratio(String p_e_ratio) {
        this.p_e_ratio = p_e_ratio;
    }

    public static void main(String[] args) {


        String str = "{\"price\": \"6.95\", \"rise_and_fall_quota\": \"-0.03\", \"rise_and_fall_range\": \"-0.43%\", \"height\": \"7.03\", \"low\": \"6.92\", \"today_open\": \"6.96\", \"yesterday_close\": \"6.98\", \"volumn\": \"2.97万\", \"turn_volume\": \"0.21亿\", \"turnover_rate\": \"0.42%\", \"amplitude\": \"1.58%\", \"p_e_ratio\": \"27.81\"}";
        CallBackSharesVO vo = JSONObject.parseObject(str, CallBackSharesVO.class);
        System.out.println(vo.toString());
    }
}
