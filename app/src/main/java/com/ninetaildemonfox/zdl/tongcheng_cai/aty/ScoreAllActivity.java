package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.Color3Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ColorAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.entity.WFootallBean;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/9 14:21
 * 功能描述： 比分展开全部
 * 联系方式：1037438704@qq.com
 */
public class ScoreAllActivity extends BaseActivity {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_sure)
    TextView textSure;
    @BindView(R.id.recyclerViewWin)
    RecyclerView recyclerViewWin;
    @BindView(R.id.recyclerViewFlat)
    RecyclerView recyclerViewFlat;
    @BindView(R.id.recyclerViewNegative)
    RecyclerView recyclerViewNegative;
    @BindView(R.id.recyclerViewLing)
    RecyclerView recyclerViewLing;
    @BindView(R.id.recyclerViewfuyi)
    RecyclerView recyclerViewfuyi;
    @BindView(R.id.recyclerViewTotal)
    RecyclerView recyclerViewTotal;
    @BindView(R.id.recyclerViewDoubleResult)
    RecyclerView recyclerViewDoubleResult;
    @BindView(R.id.ll_gone_one)
    LinearLayout ll_gone_one;
    @BindView(R.id.ll_gone_two)
    LinearLayout ll_gone_two;
    @BindView(R.id.ll_gone_three)
    LinearLayout ll_gone_three;

    private WFootallBean.DataBeanXXX.ListBean list = new WFootallBean.DataBeanXXX.ListBean();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_score_all;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        imageleftFinish.setVisibility(View.VISIBLE);
        String scoreview = getIntent().getExtras().getString("scoreview");
        String zhudui = getIntent().getExtras().getString("zhudui");
        String kedui = getIntent().getExtras().getString("kedui");
        textCenter.setText(zhudui+"vs"+kedui);
        if (!TextUtils.isEmpty(scoreview)) {
            if (scoreview.equals("2")) {
                ll_gone_one.setVisibility(View.VISIBLE);
                ll_gone_two.setVisibility(View.VISIBLE);
                ll_gone_three.setVisibility(View.VISIBLE);
            } else {
                ll_gone_one.setVisibility(View.GONE);
                ll_gone_two.setVisibility(View.GONE);
                ll_gone_three.setVisibility(View.GONE);
            }
        }

        //第一个
        recyclerViewLing.setLayoutManager(new GridLayoutManager(me, 3));
        List<String> list4 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list4.add("");
        }
        ColorAdp colorAdp4 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewLing.setAdapter(colorAdp4);
//        colorAdp4.setNewData(list4);
        colorAdp4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //第二个
        recyclerViewfuyi.setLayoutManager(new GridLayoutManager(me, 3));
        ColorAdp colorAdp5 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewfuyi.setAdapter(colorAdp4);
//        colorAdp5.setNewData(list4);
        colorAdp5.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //第三个
        recyclerViewTotal.setLayoutManager(new GridLayoutManager(me, 4));
        ColorAdp colorAdp6 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewTotal.setAdapter(colorAdp6);
        List<String> sange = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            sange.add("");
        }
//        colorAdp6.setNewData(sange);
        colorAdp6.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //第四个
        recyclerViewDoubleResult.setLayoutManager(new GridLayoutManager(me, 3));
        ColorAdp colorAdp7 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewDoubleResult.setAdapter(colorAdp7);
        sange.add("");
//        colorAdp7.setNewData(sange);
        colorAdp7.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //--------------------------------------------比分-------------------------------------------

        //胜

        WFootallBean.DataBeanXXX.ListBean.PlayValueBean play_value = list.getPlay_value();
        if (play_value == null){
            return;
        }
        List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean> score_value = play_value.getScore_value();
        if(score_value == null || score_value.size() == 0){
            return;
        }

        for (int i = 0; i < score_value.size(); i++) {
            switch (i){
                case 0:
                    //胜
                    List<WFootallBean.DataBeanXXX.ListBean.PlayValueBean.ScoreValueBean.DataBeanXX> data = score_value.get(i).getData();
                    if (data != null){
                        recyclerViewWin.setLayoutManager(new GridLayoutManager(me, 5));
                        final Color3Adp colorAdp = new Color3Adp(R.layout.item_score_item);
                        recyclerViewWin.setAdapter(colorAdp);
                        colorAdp.setNewData(data);
                        colorAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ToastUtil.show("点击了");
                            }
                        });
                    }

                    break;
                case 1:
                    //平
                    //ping(helper,score_value.get(i));
                    break;
                case 2:
                    //负
                    //fu(helper,score_value.get(i));
                    break;
                default:
            }
        }



        //平
        recyclerViewFlat.setLayoutManager(new GridLayoutManager(me, 5));
        ColorAdp colorAdp2 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewFlat.setAdapter(colorAdp2);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list2.add("");
        }
//        colorAdp2.setNewData(list2);
        colorAdp2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });

        //负
        recyclerViewNegative.setLayoutManager(new GridLayoutManager(me, 5));
        ColorAdp colorAdp3 = new ColorAdp(R.layout.item_color_list_adp);
        recyclerViewNegative.setAdapter(colorAdp3);
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            list3.add("");
        }
//        colorAdp3.setNewData(list3);
        colorAdp3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("点击了");
            }
        });
    }

    @OnClick({R.id.image_left_finish, R.id.text_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_sure:
                finish();
                break;
            default:
        }
    }

}
