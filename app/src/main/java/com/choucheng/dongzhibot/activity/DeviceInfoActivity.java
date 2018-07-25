package com.choucheng.dongzhibot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bjkj.library.okhttp.HttpCallBack;
import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.bean.DeviceInfoBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.choucheng.dongzhibot.view.TitleView;
import com.vondear.rxtool.view.RxToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/7/3.
 * 商户巡检，设备信息
 */

public class DeviceInfoActivity extends BaseActivity {
    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.style)
    TextView style;
    @Bind(R.id.contactsName)
    TextView contactsName;
    @Bind(R.id.contactsPhone)
    TextView contactsPhone;

    private String pos_no;
    private String device_id;
    private DeviceInfoBean.DeviceInfoData infoData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_deviceinfo;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        super.initData();

        Intent intent = getIntent();
        pos_no = intent.getStringExtra("pos_no");

        DongZhiModle.deviceInfo(pos_no, new HttpCallBack<DeviceInfoBean.DeviceInfoData>() {
            @Override
            public void success(DeviceInfoBean.DeviceInfoData deviceInfoData) {
                if (deviceInfoData==null){
                    RxToast.normal("未获取到数据");
                }
                infoData = deviceInfoData;
                code.setText(deviceInfoData.merchant_no);
                name.setText(deviceInfoData.name);
                address.setText(deviceInfoData.address);
                number.setText(deviceInfoData.pos_no);
                style.setText(deviceInfoData.pos_fac);
                contactsName.setText(deviceInfoData.link_name);
                contactsPhone.setText(deviceInfoData.link_phone);
                device_id = deviceInfoData.device_id;
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }


    @OnClick(R.id.confirm)
    public void onViewClicked() {
//        finish();//
        Intent intent = new Intent(DeviceInfoActivity.this,ErrorListActivity.class);
        intent.putExtra("device_id",device_id);
        startActivity(intent);
    }


}
