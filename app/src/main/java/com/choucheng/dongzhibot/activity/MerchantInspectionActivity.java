package com.choucheng.dongzhibot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.view.TitleView;
import com.vondear.rxtool.view.RxToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/7/2.
 *
 * 商户巡检，开始巡检
 */

public class MerchantInspectionActivity extends BaseActivity {
    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.confirm)
    TextView confirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_inspect;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        super.initListener();
        title.setmRightImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(mActivity,ActivityScanerCode.class));
            }
        });
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        String device_id = code.getText().toString().trim();
        if (TextUtils.isEmpty(device_id)){
            RxToast.normal("请输入设备编号");
            return;
        }
        Intent intent =new Intent(mActivity,DeviceInfoActivity.class);
        intent.putExtra("pos_no",code.getText().toString().trim());
        startActivity(intent);
    }
}
