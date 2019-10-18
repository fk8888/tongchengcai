package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/22
 * 功能描述： 第一层
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class DataBean {
    public static List<Map<String, String>> documentaryScreening() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new ArrayMap<>();
        map1.put("name", "全部");
        map1.put("type", "0");
        list.add(map1);
        Map<String, String> map2 = new ArrayMap<>();
        map2.put("name", "实力");
        map2.put("type", "0");
        list.add(map2);
        Map<String, String> map3 = new ArrayMap<>();
        map3.put("name", "盈利");
        map3.put("type", "0");
        list.add(map3);
        Map<String, String> map4 = new ArrayMap<>();
        map4.put("name", "人气");
        map4.put("type", "0");
        list.add(map4);
        return list;
    }

    public static List<Map<String, String>> documentaryType() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new ArrayMap<>();
        map1.put("name", "全部跟单");
        map1.put("type", "0");
        list.add(map1);
        Map<String, String> map2 = new ArrayMap<>();
        map2.put("name", "用户跟单");
        map2.put("type", "0");
        list.add(map2);
        Map<String, String> map3 = new ArrayMap<>();
        map3.put("name", "我的跟单");
        map3.put("type", "0");
        list.add(map3);
        return list;
    }

}