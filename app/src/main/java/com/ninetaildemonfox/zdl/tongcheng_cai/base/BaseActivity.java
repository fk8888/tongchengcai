package com.ninetaildemonfox.zdl.tongcheng_cai.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.kongzue.baseokhttp.listener.ResponseInterceptListener;
import com.kongzue.baseokhttp.util.BaseOkHttp;
import com.kongzue.dialog.v3.WaitDialog;
import com.lljjcoder.Constant;
import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.aty.LoginActivity;
import com.ninetaildemonfox.zdl.tongcheng_cai.config.Constants;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.AppManager;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.Preferences;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.ToastUtil;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/8/23
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public abstract class BaseActivity extends AppCompatActivity implements Constants {
    public String token;
    public View viewError;
    public AppCompatActivity me;
    public List<String> list;
    public Bundle bundle;
    /**
     * 当前页码
     */
    protected int currentpage = 1;
    private int REQUEST_CODE_PERMISSION = 0x00099;

    protected abstract int getLayoutResource();

    protected abstract void onInitialization(Bundle bundle);

    //    protected SwipeBackHelper mSwipeBackHelper;
    //权限申请回调
    private OnPermissionResponseListener onPermissionResponseListener;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        me = this;
        token = Preferences.getInstance().getString(me, "user", "token");
        viewError = getLayoutInflater().inflate(R.layout.item_null_activity, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        bundle = new Bundle();
        AppManager.getInstance().pushActivity(me);
        setStatusBar();
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
            bind = ButterKnife.bind(this);
        }
        httpInterceptlistener();
        onInitialization(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        AppManager.getInstance().deleteActivity(me);
    }

    public void finishActivity() {
        super.finish();
    }


    //获取状态栏的高度
    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        try {
            return getResources().getDimensionPixelSize(resourceId);
        } catch (Resources.NotFoundException e) {
            return 0;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        token = Preferences.getInstance().getString(me, "user", "token");
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


    public static void ShowToast(Context me, String msg) {
        Toast toast = Toast.makeText(me, msg, Toast.LENGTH_LONG);
        toast.setText(msg);
        toast.show();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     * <p>
     * 警告：此处除了用户拒绝外，唯一可能出现无法获取权限或失败的情况是在AndroidManifest.xml中未声明权限信息
     * Android6.0+即便需要动态请求权限（重点）但不代表着不需要在AndroidManifest.xml中进行声明。
     *
     * @param permissions                  请求的权限
     * @param onPermissionResponseListener 回调监听器
     */
    public void requestPermission(OnPermissionResponseListener onPermissionResponseListener, String... permissions) {
        this.onPermissionResponseListener = onPermissionResponseListener;
        if (checkPermissions(permissions)) {
            if (onPermissionResponseListener != null) {
                onPermissionResponseListener.onSuccess(permissions);
            }
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    public interface OnPermissionResponseListener {
        void onSuccess(String[] permissions);

        void onFail();
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String... permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    /**
     * 启动当前应用设置页面
     */
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public static void start(AppCompatActivity mContext, Bundle bundle, Class up) {
        Intent intent = new Intent(mContext, up);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public static void start(AppCompatActivity mContext, Class up) {
        mContext.startActivity(new Intent(mContext, up));
    }

    public static void start(AppCompatActivity mContext, Class up, int tag) {
        mContext.startActivityForResult(new Intent(mContext, up), tag);
    }

    public static void start(AppCompatActivity mContext, Bundle bundle, Class up, int tag) {
        Intent intent = new Intent(mContext, up);
        intent.putExtras(bundle);
        mContext.startActivityForResult(intent, tag);
    }

    //返回传参
    public static void finishStart(AppCompatActivity mContext, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        mContext.setResult(RESULT_OK, intent);
        mContext.finish();
    }


    public void whitchBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.whileColor), 0);
        StatusBarUtil.setLightMode(me);
    }

    public void TransparentBar() {
//        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        StatusBarUtil.setTranslucent(this, 0);
    }

    private void httpInterceptlistener() {
        BaseOkHttp.responseInterceptListener = new ResponseInterceptListener() {
            @Override
            public boolean onResponse(Context context, String url, String response, Exception error) {
                WaitDialog.dismiss();
                if (error == null) {
                    if (response == null) {
                        return false;
                    }
                    Map<String, String> map = JSONUtils.parseKeyAndValueToMap(response);
                    if (map == null) {
                        return false;
                    }
                    if (map.get("code").equals("0")) {
                        ToastUtil.show(map.get("message"));
                        return false;
                    }
                    if (map.get("code").equals("-1")) {
                        start(me, LoginActivity.class);
                        return false;
                    }
                    if (map.get("code").equals("1")) {
                        return true;
                    }
                    return false;
                } else {
                    ToastUtil.show("网路连接失败");
                    return false;
                }
            }
        };
    }

    /**
     * 点击页面空白处时，让键盘消失
     *
     * @param event 事件
     * @return boolean
     */

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {

                if (getCurrentFocus().getWindowToken() != null) {

                    mInputMethodManager.hideSoftInputFromWindow(

                            getCurrentFocus().getWindowToken(),

                            InputMethodManager.HIDE_NOT_ALWAYS);

                }

            }

        }

        return super.onTouchEvent(event);

    }

    @Override

    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            View v = getCurrentFocus();

            if (isShouldHideKeyboard(v, ev)) {

                hideKeyboard(v.getWindowToken());

            }

        }

        return super.dispatchTouchEvent(ev);

    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v     View
     * @param event 事件
     * @return boolean
     */

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {

        if (v != null && (v instanceof EditText)) {

            int[] l = {0, 0};

            v.getLocationInWindow(l);

            int left = l[0],

                    top = l[1],

                    bottom = top + v.getHeight(),

                    right = left + v.getWidth();

            return !(event.getX() > left) || !(event.getX() < right)

                    || !(event.getY() > top) || !(event.getY() < bottom);

        }

// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点

        return false;

    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token1 Ibinder
     */

    private void hideKeyboard(IBinder token1) {

        if (token1 != null) {

            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            im.hideSoftInputFromWindow(token1, InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

}
