package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;

import java.util.List;
import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/21
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class ImageViewAdp extends BaseQuickAdapter<Map<String,String>, BaseViewHolder> {

    public ImageViewAdp(int layout_custom_frame) {
        super(layout_custom_frame);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        ImageView post_img_paths = helper.itemView.findViewById(R.id.post_img_paths);
        GlidUtils.defaultGlid(mContext, item.get("img_path"), post_img_paths);
    }
}