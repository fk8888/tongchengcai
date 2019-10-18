package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.Screen2Adp;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.ScreenPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.contract.ScreenContract;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author NineTailDemonFox
 * @date 2019/9/8 16:02
 * 功能描述： 筛选界面
 * 联系方式：1037438704@qq.com
 */
public class ScreenActivity extends BaseActivity implements ScreenContract {

    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.reyclerViewUp)
    RecyclerView reyclerViewUp;
    @BindView(R.id.reyclerViewDown)
    RecyclerView reyclerViewDown;
    @BindView(R.id.text_sure)
    TextView textSure;
    @BindView(R.id.text_cancel)
    TextView text_cancel;
    private Screen2Adp screenAdp1, screenAdp2;
    private ScreenPresenter presenter;
    private int shopDetails;
    private String play_flag, type,odds_id,match_type_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_screen;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
        initEvent();
    }

    private void initEvent() {
        screenAdp1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Map<String, String>> data = screenAdp1.getData();
                if (data == null) {
                    return;
                }
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("type", "0");
                }
                data.get(position).put("type", "1");
                screenAdp1.notifyDataSetChanged();
            }
        });
        screenAdp2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Map<String, String>> data = screenAdp2.getData();
                if (data == null) {
                    return;
                }
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).put("type", "0");
                }
                data.get(position).put("type", "1");
                screenAdp2.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        shopDetails = getIntent().getExtras().getInt("shopDetails");
        play_flag = getIntent().getExtras().getString("play_flag");
        if (shopDetails == 0) {
            type = "1";
        } else if (shopDetails == 1) {
            type = "2";
        }
        presenter = new ScreenPresenter(me, this);
        presenter.getMatchTypeList(type, play_flag);
        textCenter.setText("筛选");
        imageleftFinish.setVisibility(View.VISIBLE);
        reyclerViewUp.setLayoutManager(new GridLayoutManager(me, 4));
        reyclerViewDown.setLayoutManager(new GridLayoutManager(me, 4));

        screenAdp1 = new Screen2Adp(R.layout.item_screen_list_adp);
        screenAdp2 = new Screen2Adp(R.layout.item_screen_list_adp);
        reyclerViewUp.setAdapter(screenAdp1);
        reyclerViewDown.setAdapter(screenAdp2);
    }

    @OnClick({R.id.image_left_finish, R.id.text_sure, R.id.text_cancel})
    public void onViewClicked(View view) {
        List<Map<String, String>> data1 = screenAdp1.getData();
        List<Map<String, String>> data2 = screenAdp2.getData();
        switch (view.getId()) {
            case R.id.image_left_finish:
                finish();
                break;
            case R.id.text_sure:
                if (data1 == null) {
                    return;
                }
                for (int i = 0; i < data1.size(); i++) {
                    if (data1.get(i).get("type").equals("1")){
                        odds_id = data1.get(i).get("id");
                    }
                }
                if (data2 == null) {
                    return;
                }
                for (int i = 0; i < data2.size(); i++) {
                    if (data2.get(i).get("type").equals("1")){
                        match_type_id = data1.get(i).get("id");
                    }
                }

                //返回上一界面
                bundle.putString("match_type_id",match_type_id);
                bundle.putString("odds_id",odds_id);
                finishStart(me, bundle);
                break;
            case R.id.text_cancel:
                if (data1 == null) {
                    return;
                }
                for (int i = 0; i < data1.size(); i++) {
                    data1.get(i).put("type", "0");
                }

                if (data2 == null) {
                    return;
                }
                for (int i = 0; i < data2.size(); i++) {
                    data2.get(i).put("type", "0");
                }
                screenAdp1.notifyDataSetChanged();
                screenAdp2.notifyDataSetChanged();
                break;
            default:
        }
    }

    @Override
    public void getMatchTypeList(Map<String, String> data) {
        ArrayList<Map<String, String>> odds = JSONUtils.parseKeyAndValueToMapList(data.get("odds"));
        if (odds != null) {
            for (int i = 0; i < odds.size(); i++) {
                odds.get(i).put("type", "0");
            }
            screenAdp1.setNewData(odds);
        }
        ArrayList<Map<String, String>> match_type_list = JSONUtils.parseKeyAndValueToMapList(data.get("match_type_list"));
        if (match_type_list != null) {
            for (int i = 0; i < match_type_list.size(); i++) {
                match_type_list.get(i).put("type", "0");
            }
            screenAdp2.setNewData(match_type_list);
        }

    }
}