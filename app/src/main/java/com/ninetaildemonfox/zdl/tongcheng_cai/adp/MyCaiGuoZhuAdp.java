package com.ninetaildemonfox.zdl.tongcheng_cai.adp;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;

import java.util.List;
import java.util.Map;

public class MyCaiGuoZhuAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public String status;
    private TextView list_item_result;

    public void setStatus(String status) {
        this.status = status;
    }

    public MyCaiGuoZhuAdp(int layoutResId, @Nullable List<Map<String, String>> data) {
        super(layoutResId, data);
    }

    public MyCaiGuoZhuAdp(int item_touzhu) {
        super(item_touzhu);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.list_item_result, item.get("key"))
                .setText(R.id.list_item_value,item.get("value"));

        //list_item_result = helper.itemView.findViewById(R.id.list_caiguo_recyclerview);
        if (status.equals("4")){
            helper.setTextColor(R.id.list_item_result,mContext.getResources().getColor(R.color.colorAccent));
            helper.setTextColor(R.id.list_item_value,mContext.getResources().getColor(R.color.colorAccent));
        }
    }
}
