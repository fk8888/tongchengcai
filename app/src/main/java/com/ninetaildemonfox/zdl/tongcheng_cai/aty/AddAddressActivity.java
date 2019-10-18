package com.ninetaildemonfox.zdl.tongcheng_cai.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.presenter.AddAddressPresenter;
import com.ninetaildemonfox.zdl.tongcheng_cai.base.BaseActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author NineTailDemonFox
 * @date 2019/9/7 10:15
 * 功能描述： 添加收货地址
 * 联系方式：1037438704@qq.com
 */
public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.image_left_finish)
    ImageView imageleftFinish;
    @BindView(R.id.text_center)
    TextView textCenter;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.text_preservation)
    TextView textPreservation;
    @BindView(R.id.edit_name)
    EditText ediName;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_detailed_address)
    EditText editDetailedAddress;
    private AddAddressPresenter addAddressPresenter;
    private String receive_province;
    private String receive_city;
    private String receive_area;
    private String count;
    private Map<String, String> thisData;
    private String address_id;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        count = getIntent().getExtras().getString("count");

        if (count.equals("2")) {
            String map = getIntent().getExtras().getString("map");
            thisData = JSONUtils.parseKeyAndValueToMap(map);
            ediName.setText(thisData.get("receive_name"));
            editPhone.setText(thisData.get("receive_phone"));
            address_id = thisData.get("id");
            receive_province = thisData.get("receive_province");
            receive_city = thisData.get("receive_city");
            receive_area = thisData.get("receive_area");

            textAddress.setText(receive_province + "\t" + receive_city + "\t" + receive_area);
            editDetailedAddress.setText(thisData.get("receive_address"));
        }
        imageleftFinish.setVisibility(View.VISIBLE);
        textCenter.setText("添加收货地址");
        addAddressPresenter = new AddAddressPresenter(me);
    }

    @OnClick({R.id.image_left_finish, R.id.text_address, R.id.text_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_left_finish:
                finishActivity();
                break;
            case R.id.text_address:
                //选择地址
                start(me, SelectAddressActivity.class, 100);
                break;
            case R.id.text_preservation:
                if (count.equals("2")) {
                    addAddressPresenter.saveReceiveAddress(token,
                            address_id,
                            ediName.getText().toString().trim(),
                            editPhone.getText().toString().trim(),
                            receive_province,
                            receive_city,
                            receive_area,
                            editDetailedAddress.getText().toString().trim());
                    return;
                }
                //保存
                addAddressPresenter.addReceiveAddress(token,
                        ediName.getText().toString().trim(),
                        editPhone.getText().toString().trim(),
                        receive_province,
                        receive_city,
                        receive_area,
                        editDetailedAddress.getText().toString().trim());
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            receive_province = data.getExtras().getString("province");
            receive_city = data.getExtras().getString("city");
            receive_area = data.getExtras().getString("area");
            textAddress.setText(receive_province + "\t" + receive_city + "\t" + receive_area);
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }
}