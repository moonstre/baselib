package com.choucheng.dongzhibot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.view.MyGridView;
import com.bumptech.glide.Glide;
import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.adapter.CommonAdapter;
import com.choucheng.dongzhibot.adapter.CommonViewHolder;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.bean.ProtectBeanOrderInfo;
import com.choucheng.dongzhibot.bean.UploadBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.vondear.rxtool.view.RxToast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by admin on 2018/7/17.
 * 维护工单信息
 */

public class ProtectOrderInfoActivity extends BaseActivity {
    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.user)
    TextView user;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.machain_number)
    TextView machainNumber;
    @Bind(R.id.des)
    TextView des;
    @Bind(R.id.protect_des)
    EditText protectDes;
    @Bind(R.id.photo)
    MyGridView mPhoto;
    @Bind(R.id.commit)
    TextView commit;

    private String xunjianid="";
    private String d_id = "";
    private String odd_number="";
    private CommonAdapter adapter;
    ArrayList<String> photos = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_protect_order_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        xunjianid = intent.getStringExtra("xunjianid");
        d_id = intent.getStringExtra("d_id");

        photos.add("");
        adapter = new CommonAdapter<String>(ProtectOrderInfoActivity.this, photos, R.layout.item_merchant_image) {
            @Override
            protected void convertView(int position, View item, String s) {
                if (position == photos.size() - 1) {
                    Glide.with(ProtectOrderInfoActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                }
            }
        };
        mPhoto.setAdapter(adapter);
        getData();
    }

    @Override
    public void initListener() {
        super.initListener();

        mPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == photos.size() - 1) {
                    openGallery(new ArrayList<String>(), 5, true, 1);
                }
            }
        });
    }

    private void openGallery(ArrayList<String> datas, int number, boolean isMulti, int requestCode) {
        MultiImageSelector selector = MultiImageSelector.create(mActivity);
        selector.showCamera(true);//是否显示相机
        selector.count(number);//显示照片个数

        if (isMulti) {
            selector.multi();
        } else {
            selector.single();
        }
        selector.origin(datas);
        selector.start(mActivity, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode==1){
                photos = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                photos.add("");
                mPhoto.setAdapter(new CommonAdapter<String>(ProtectOrderInfoActivity.this, photos, R.layout.item_merchant_image) {
                    @Override
                    protected void convertView(int position, View item, String s) {
                        if (position == photos.size() - 1) {
                            Glide.with(ProtectOrderInfoActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                        }else {
                            Glide.with(ProtectOrderInfoActivity.this).asBitmap().load(s).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        }
                    }
                });
                adapter.notifyDataSetChanged();

                for (int i=0;i<photos.size();i++){
                    if (!TextUtils.isEmpty(photos.get(i))){
                        DongZhiModle.testUpLoad(photos, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                            @Override
                            public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                                urls.add(uploadData.path);
                                for (int i=0;i<uploadData.size();i++){
                                    urls.add(uploadData.get(i).path);
                                }
                            }

                            @Override
                            public void fail(String errorStr) {

                            }
                        });
                    }
                }
            }
        }
    }

    private void getData() {

        DongZhiModle.protectOrderInfo(xunjianid, d_id, new HttpCallBack<ProtectBeanOrderInfo.ProtectBeanOrderInfoData>() {
            @Override
            public void success(ProtectBeanOrderInfo.ProtectBeanOrderInfoData protectBeanOrderInfoData) {
                code.setText(protectBeanOrderInfoData.merchant_no);
                name.setText(protectBeanOrderInfoData.name);
                address.setText(protectBeanOrderInfoData.address);
                user.setText(protectBeanOrderInfoData.link_name);
                phone.setText(protectBeanOrderInfoData.link_phone);
                machainNumber.setText(protectBeanOrderInfoData.pos_no);
                des.setText(protectBeanOrderInfoData.service_mark);
                odd_number=protectBeanOrderInfoData.odd_number;
            }

            @Override
            public void fail(String errorStr) {

            }
        });

    }

    @OnClick(R.id.commit)
    public void onViewClicked() {

        DongZhiModle.commitProtectInfo(odd_number,protectDes.getText().toString().trim(),urls,new HttpCallBack<String>(){
            @Override
            public void success(String o) {
                RxToast.normal("提交审核成功");
            }

            @Override
            public void fail(String errorStr) {
                RxToast.normal("提交审核成功");
            }
        });
    }
}
