package com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter;

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

public interface ForumContract {
    void selectPostCommentsList(List<Map<String, String>> data);

    void delPost(int item);
}
