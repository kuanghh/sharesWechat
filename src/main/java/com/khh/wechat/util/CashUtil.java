package com.khh.wechat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 951087952@qq.com on 2018/4/6 0006.
 * 缓存帮助类
 */
public class CashUtil {

    /**
     * 存放msgId的缓存值
     *
     * key ---- > msgId
     * value -----> button_key
     */
    public static Map<String,Object> cashMap = new HashMap<>();

}
