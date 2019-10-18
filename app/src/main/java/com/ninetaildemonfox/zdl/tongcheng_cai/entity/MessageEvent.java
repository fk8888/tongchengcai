package com.ninetaildemonfox.zdl.tongcheng_cai.entity;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/7
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class MessageEvent {
    public String msg;
    public int potation;

    public MessageEvent(int potation) {
        this.potation = potation;
    }

    public MessageEvent(String msg) {
        this.msg = msg;
    }
}
