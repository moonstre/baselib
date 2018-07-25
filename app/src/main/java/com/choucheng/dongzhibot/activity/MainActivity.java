package com.choucheng.dongzhibot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bjkj.library.okhttp.HttpCallBack;
import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.bean.MainBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.choucheng.dongzhibot.utils.LogUtils;
import com.choucheng.dongzhibot.view.DialogUtil;

/**
 * Created by liyou on 2018/6/4.
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.promotion)
    ImageView mPromotion;
    @Bind(R.id.business_order)
    ImageView mBusinessOrder;
    @Bind(R.id.inspection)
    ImageView mInspection;
    @Bind(R.id.maintain_order)
    ImageView mMaintainOrder;
    @Bind(R.id.user_info)
    ImageView mUserInfo;
    @Bind(R.id.sys_update)
    ImageView mSysUpdate;
    @Bind(R.id.history)
    LinearLayout mHistory;
    @Bind(R.id.title)
    TextView mTitle;

    @Override
    public int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        DongZhiModle.autoLogin(mActivity, new HttpCallBack() {
            @Override
            public void success(Object o) {
                DongZhiModle.main(new HttpCallBack<MainBean.MainData>() {
                    @Override
                    public void success(MainBean.MainData mainData) {

                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    int i = 0;

    @OnClick({
            R.id.promotion, R.id.business_order, R.id.inspection, R.id.maintain_order, R.id.user_info,
            R.id.sys_update, R.id.history, R.id.title
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.promotion:
                startActivity(new Intent(MainActivity.this, MerchantPromotionActivity.class));
                break;
            case R.id.business_order:
                startActivity(new Intent(MainActivity.this, InstallOrderActivity.class));
                break;
            case R.id.inspection:
                startActivity(new Intent(MainActivity.this, MerchantInspectionActivity.class));
                break;
            case R.id.maintain_order:
                startActivity(new Intent(MainActivity.this, ProtectOrderActivity.class));
                break;
            case R.id.user_info:
                startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                break;
            case R.id.sys_update:
                DialogUtil.showLastestDialog(MainActivity.this);
                break;
            case R.id.history:
                startActivity(new Intent(MainActivity.this, HistoryTaskActivity.class));
                break;
            case R.id.title:
                i++;
                if (i > 5) {
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                }
                break;
        }
    }
}
