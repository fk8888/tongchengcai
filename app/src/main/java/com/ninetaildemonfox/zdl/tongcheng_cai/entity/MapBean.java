package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/25
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MapBean implements Serializable{
    public Map<String,String> map;

    public MapBean(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "map=" + map +
                '}';
    }
}
