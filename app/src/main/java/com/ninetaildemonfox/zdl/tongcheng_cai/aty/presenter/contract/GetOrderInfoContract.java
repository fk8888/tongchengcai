package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract;

import java.util.Map;

public interface GetOrderInfoContract {
    void  getOrder(Map<String, String> data);

    void cancleList(Map<String, String> data);
}

