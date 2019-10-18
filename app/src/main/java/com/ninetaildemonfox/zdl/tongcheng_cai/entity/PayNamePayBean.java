package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/7/19
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class PayNamePayBean {
    public String name;
    public boolean choice;
    public int icon;
    public int payCount;


    public PayNamePayBean(String name, boolean choice, int icon) {
        this.name = name;
        this.choice = choice;
        this.icon = icon;
    }

    public PayNamePayBean(String name, boolean choice, int icon, int payCount) {
        this.name = name;
        this.choice = choice;
        this.icon = icon;
        this.payCount = payCount;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public boolean getChoice() {
        return choice;
    }
}
