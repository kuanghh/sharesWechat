package com.khh.web.enm;

import java.util.HashMap;

/**
 * Created by 951087952@qq.com on 2018/4/1.
 */
public enum  SharesParamEnum {

    open_price("open_price", "开盘价"),
    close_price("close_price", "收盘价"),
    ceilling_price("ceilling_price", "最高价"),
    floor_price("floor_price", "最低价"),
    rise_and_fall_range("rise_and_fall_range", "涨跌幅"),
    rise_and_fall_quota("rise_and_fall_quota", "涨跌额"),
    volume("volume", "成交量"),
    turn_volume("turn_volume", "成交额"),
    turnover_rate("turnover_rate", "换手率"),
    amplitude("amplitude", "振幅"),
    p_e_ratio("p_e_ratio", "市盈率")

    ;

    private String field;
    private String desc;

    SharesParamEnum(String field, String desc) {
        this.field = field;
        this.desc = desc;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
