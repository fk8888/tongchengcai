package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/8
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ShopDetailsBean {
    private String name;

    private int icon;

    public ShopDetailsBean(String name, int icon) {
        this.name = name;
        this.icon = icon;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "ShopDetailsBean{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                '}';
    }
}
