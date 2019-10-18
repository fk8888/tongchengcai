package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public interface ForumTextContract {
    void findPostInfo(Map<String, String> data);
    void selectPostCommentsList(List<Map<String, String>> data);
}
