package com.choucheng.dongzhibot.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.okhttp.OkHttpUtils;
import com.bjkj.library.utils.GlideUtils;
import com.bumptech.glide.Glide;
import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.adapter.CommonAdapter;
import com.choucheng.dongzhibot.adapter.CommonViewHolder;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.bean.MerchantInfoBean;
import com.choucheng.dongzhibot.bean.MerchantInfoSeeBean;
import com.choucheng.dongzhibot.bean.UploadBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.choucheng.dongzhibot.utils.PermissionUtil;
import com.choucheng.dongzhibot.view.DialogUtil;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by liyou on 2018/6/5.
 */

public class MerchantInfoCollectActivity extends BaseActivity {

    public static final int REQUEST_HEAD = 0;
    public static final int REQUEST_FRONT = 1;
    public static final int REQUEST_INNER = 2;
    public static final int REQUEST_BUISSNESS = 3;
    public static final int REQUEST_LEGAL = 4;
    public static final int REQUEST_BANK = 5;
    public static final int REQUEST_OTHERS = 6;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.address)
    EditText mAddress;
    @Bind(R.id.legal_phone)
    EditText mLegalPhone;
    @Bind(R.id.document_number)
    EditText mDocumentNumber;
    @Bind(R.id.regist_name)
    EditText mRegistName;
    @Bind(R.id.regist_number)
    EditText mRegistNumber;
    @Bind(R.id.regist_address)
    EditText mRegistAddress;
    @Bind(R.id.contacts)
    EditText mContacts;
    @Bind(R.id.contacts_phone)
    EditText mContactsPhone;
    @Bind(R.id.retail)
    RadioButton mRetail;
    @Bind(R.id.wholesale)
    RadioButton mWholesale;
    @Bind(R.id.type)
    RadioGroup mType;
    @Bind(R.id.style)
    EditText mStyle;
    @Bind(R.id.protocol_number)
    EditText mProtocolNumber;
    @Bind(R.id.rb_trend)
    RadioButton mRbTrend;
    @Bind(R.id.rb_reserve)
    RadioButton mRbReserve;
    @Bind(R.id.ll_trend)
    RadioGroup mLlTrend;
    @Bind(R.id.note)
    EditText mNote;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.add_head)
    ImageView addHead;
    @Bind(R.id.iv_front)
    ImageView ivFront;
    @Bind(R.id.add_front)
    ImageView addFront;
    @Bind(R.id.iv_inner)
    ImageView ivInner;
    @Bind(R.id.add_inner)
    ImageView addInner;
    @Bind(R.id.iv_business)
    ImageView ivBusiness;
    @Bind(R.id.add_business)
    ImageView addBusiness;
    @Bind(R.id.legal)
    GridView mLegal;
    @Bind(R.id.bank)
    GridView mBank;
    @Bind(R.id.others)
    GridView mOthers;
    @Bind(R.id.save)
    TextView mSave;
    @Bind(R.id.commit)
    TextView mCommit;
    ArrayList<String> others = new ArrayList<>();
    ArrayList<String> heads = new ArrayList<>();
    ArrayList<String> fronts = new ArrayList<>();
    ArrayList<String> inners = new ArrayList<>();
    ArrayList<String> businesss = new ArrayList<>();
    ArrayList<String> legals = new ArrayList<>();
    ArrayList<String> banks = new ArrayList<>();
    String type = "1";
    String trend = "1";
    String id;
    String headUrl="";
    String frontUrl="";
    String innerUrl="";
    String businesUrl="";
    String legalUrl="";
    String bankUrl="";
    String otherUrl="";
    public MerchantInfoBean.MerchantInfoData merchantInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_merchant_info;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
         id = intent.getStringExtra("id");
        if (id != null) {
            DongZhiModle.updateMerchantInfo(id, new HttpCallBack<MerchantInfoBean.MerchantInfoData>() {
                @Override
                public void success(MerchantInfoBean.MerchantInfoData merchantInfoData) {
                    merchantInfo = merchantInfoData;
                    if (merchantInfo!=null){
                        mName.setText(merchantInfo.name);
                        mAddress.setText(merchantInfo.address);
                        mLegalPhone.setText(merchantInfo.tel);
                       mDocumentNumber.setText(merchantInfo.port);
                       mRegistName.setText(merchantInfo.reg_name);
                       mRegistNumber.setText(merchantInfo.reg_no);
                       mRegistAddress.setText(merchantInfo.reg_address);
                       mContacts.setText(merchantInfo.link_name);
                       mContactsPhone.setText(merchantInfo.link_phone);
                       if ("1".equals(merchantInfo.buss_type)){
                           mRetail.setChecked(true);
                       }else if("2".equals(merchantInfo.buss_type)){
                           mWholesale.setChecked(true);
                       }
                       mStyle.setText(merchantInfo.buss_cate);
                       mProtocolNumber.setText(merchantInfo.assign_no);
                        if ("1".equals(merchantInfo.buss_type)){
                            mRbTrend.setChecked(true);
                        }else if("2".equals(merchantInfo.buss_type)){
                            mRbReserve.setChecked(true);
                        }
                        if (!TextUtils.isEmpty(merchantInfo.top_photo)){
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(merchantInfo.top_photo).into(ivHead);
                        }

                        if (!TextUtils.isEmpty(merchantInfo.head_photo)){
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(merchantInfo.top_photo).into(ivFront);
                        }

                        if (!TextUtils.isEmpty(merchantInfo.inside_photo)){
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(merchantInfo.top_photo).into(ivInner);
                        }
                        final ArrayList<String> legalsTemp = new ArrayList<>();
                        final  ArrayList<String> banksTemp = new ArrayList<>();
                        final  ArrayList<String> othersTemp = new ArrayList<>();


                        if (!TextUtils.isEmpty(merchantInfo.legal_photo)){
                            String[] legalsStr;
                            legalsStr = merchantInfo.legal_photo.split(",");
                            for (int i =0;i<legalsStr.length;i++){
                                legalsTemp.add(legalsStr[i]);
                            }
                            legalsTemp.add("");
                            mLegal.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, legalsTemp, R.layout.item_merchant_image) {
                                @Override
                                protected void convertView(int position, View item, String s) {
                                    if (position == legalsTemp.size() - 1) {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                                    }else {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                                    }
                                }
                            });
                        }

                        if (!TextUtils.isEmpty(merchantInfo.bank_photo)){
                            String[] banksStr = merchantInfo.bank_photo.split(",");
                            for (int i =0;i<banksStr.length;i++){
                                banksTemp.add(banksStr[i]);
                            }
                            banksTemp.add("");

                            mBank.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, banksTemp, R.layout.item_merchant_image) {
                                @Override
                                protected void convertView(int position, View item, String s) {
                                    if (position == banksTemp.size() - 1) {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                                    }else {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                                    }
                                }
                            });
                        }

                        if (!TextUtils.isEmpty( merchantInfo.other_photo)){
                            String[] othersStr = merchantInfo.other_photo.split(",");
                            for (int i =0;i<othersStr.length;i++){
                                othersTemp.add(othersStr[i]);
                            }
                            othersTemp.add("");

                            mOthers.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, othersTemp, R.layout.item_merchant_image) {
                                @Override
                                protected void convertView(int position, View item, String s) {
                                    if (position == legalsTemp.size() - 1) {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                                    }else {
                                        Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                                    }
                                }
                            });
                        }

                    }
                }

                @Override
                public void fail(String errorStr) {

                }
            });
        }
        others.add("");
        mOthers.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, others, R.layout.item_merchant_image) {
            @Override
            protected void convertView(int position, View item, String s) {
                if (position == others.size() - 1) {
                    Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                }
            }
        });


        banks.add("");
        mBank.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, banks, R.layout.item_merchant_image) {
            @Override
            protected void convertView(int position, View item, String s) {
                if (position == banks.size() - 1) {
                    Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                }
            }
        });

        legals.add("");
        mLegal.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, legals, R.layout.item_merchant_image) {
            @Override
            protected void convertView(int position, View item, String s) {
                if (position == legals.size() - 1) {
                    Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));
                }
            }
        });
        if (!checkPermission()) {
            PermissionUtil.requestPermission(MerchantInfoCollectActivity.this, 0, PermissionUtil.getCameraPermission());
        }
    }

    public boolean checkPermission() {

        return PermissionUtil.hasPermission(MerchantInfoCollectActivity.this, PermissionUtil.getCameraPermission());
    }

    @Override
    public void initListener() {
        super.initListener();
        mOthers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == others.size() - 1) {
                    openGallery(new ArrayList<String>(), 2, true, REQUEST_OTHERS);
                }
            }
        });

        mBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == banks.size() - 1) {
                    openGallery(new ArrayList<String>(), 2, true, REQUEST_BANK);
                }
            }
        });

        mLegal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == legals.size() - 1) {
                    openGallery(new ArrayList<String>(), 2, true, REQUEST_LEGAL);
                }
            }
        });

        mType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.retail) {
                    type = "1";
                } else if (i == R.id.wholesale) {
                    type = "2";
                }
            }
        });

        mLlTrend.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_trend) {
                    trend = "1";
                } else if (i == R.id.rb_reserve) {
                    trend = "2";
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_HEAD) {
                heads = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                ivHead.setVisibility(View.VISIBLE);
                GlideUtils.loadImageView(mActivity, heads.get(0), ivHead);
                DongZhiModle.testUpLoad(heads, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        headsUrl.clear();
//                        headsUrl.add(uploadData.path);
//                        getUrl(headUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            headUrl=tempStr;

                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
            } else if (requestCode == REQUEST_FRONT) {
                fronts = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                ivFront.setVisibility(View.VISIBLE);
                GlideUtils.loadImageView(mActivity, fronts.get(0), ivFront);
                DongZhiModle.testUpLoad(fronts, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        frontsUrl.clear();
//                        frontsUrl.add(uploadData.path);
//                        getUrl(frontUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            frontUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
            } else if (requestCode == REQUEST_INNER) {
                inners = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                ivInner.setVisibility(View.VISIBLE);
                GlideUtils.loadImageView(mActivity, inners.get(0), ivInner);
                DongZhiModle.testUpLoad(inners, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        innersUrl.clear();
//                        innersUrl.add(uploadData.);
//                        getUrl(innerUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            innerUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
            } else if (requestCode == REQUEST_BUISSNESS) {
                businesss = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                ivBusiness.setVisibility(View.VISIBLE);
                GlideUtils.loadImageView(mActivity, businesss.get(0), ivBusiness);
                DongZhiModle.testUpLoad(businesss, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        businesssUrl.clear();
//                        businesssUrl.add(uploadData.path);
//                        getUrl(businesUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            businesUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
            } else if (requestCode == REQUEST_LEGAL) {
                legals.clear();
                legals = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                removeNull(legals);
                legals.add("");

                DongZhiModle.testUpLoad(legals, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        legalsUrl.clear();
//                        legalsUrl.addAll(uploadData);
//                        getUrl(legalUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            legalUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
                mLegal.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, legals, R.layout.item_merchant_image) {
                    @Override
                    protected void convertView(int position, View item, String s) {
                        if (position == legals.size() - 1) {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        } else {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(s).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        }

                    }
                });
                calGridViewWidthAndHeigh(3, mLegal);
            } else if (requestCode == REQUEST_BANK) {
                banks.clear();
                banks = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                removeNull(banks);
                banks.add("");

                DongZhiModle.testUpLoad(banks, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {
                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        getUrl(bankUrl,uploadData);

                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            bankUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
                mBank.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, banks, R.layout.item_merchant_image) {
                    @Override
                    protected void convertView(int position, View item, String s) {
                        if (position == banks.size() - 1) {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        } else {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(s).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        }

                    }
                });
                calGridViewWidthAndHeigh(3, mBank);
            } else if (requestCode == REQUEST_OTHERS) {
                others.clear();
                others = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                removeNull(others);
                others.add("");

                DongZhiModle.testUpLoad(banks, new HttpCallBack<ArrayList<UploadBean.UploadData>>() {

                    @Override
                    public void success(ArrayList<UploadBean.UploadData> uploadData) {
//                        getUrl(otherUrl,uploadData);
                        String tempStr="";
                        for (int i=0;i<uploadData.size();i++){
                            if (i!=(uploadData.size()-1)){
                                tempStr+=uploadData.get(i).path+",";
                            }else {
                                tempStr+=uploadData.get(i).path;
                            }
                            otherUrl=tempStr;
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
                mOthers.setAdapter(new CommonAdapter<String>(MerchantInfoCollectActivity.this, others, R.layout.item_merchant_image) {
                    @Override
                    protected void convertView(int position, View item, String s) {
                        if (position == others.size() - 1) {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(R.mipmap.add_image).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        } else {
                            Glide.with(MerchantInfoCollectActivity.this).asBitmap().load(s).into(((ImageView) CommonViewHolder.get(item, R.id.image)));

                        }

                    }
                });
                calGridViewWidthAndHeigh(3, mOthers);
            }
        }
    }
//    private void getUrl(String  str,ArrayList<UploadBean.UploadData> da){
//        String tempStr="";
//        for (int i=0;i<da.size();i++){
//            if (i!=(da.size()-1)){
//                tempStr+=da.get(i)+",";
//            }else {
//                tempStr+=da.get(i);
//            }
//            str=tempStr;
//        }
//    }

    private void removeNull(ArrayList<String > list){

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            // index and number
            String str = it.next();
            if (TextUtils.isEmpty(str)) {
                it.remove();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }

    private void openGallery(ArrayList<String> datas, int number, boolean isMulti, int requestCode) {
        MultiImageSelector selector = MultiImageSelector.create(MerchantInfoCollectActivity.this);
        selector.showCamera(true);//是否显示相机
        selector.count(number);//显示照片个数

        if (isMulti) {
            selector.multi();
        } else {
            selector.single();
        }
        selector.origin(datas);
        selector.start(MerchantInfoCollectActivity.this, requestCode);
    }

    @OnClick({R.id.add_head, R.id.add_front, R.id.add_inner, R.id.add_business, R.id.commit, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_head:
                openGallery(heads, 1, false, REQUEST_HEAD);
                break;
            case R.id.add_front:
                openGallery(heads, 1, false, REQUEST_FRONT);
                break;
            case R.id.add_inner:
                openGallery(heads, 1, false, REQUEST_INNER);
                break;

            case R.id.add_business:
                openGallery(businesss, 1, false, REQUEST_BUISSNESS);
                break;
            case R.id.save:
                commitData(false);
//                DialogUtil.showTipDialog(mActivity,"提示","信息保存成功","确定");
                break;
            case R.id.commit:
                commitData(true);
//                DialogUtil.showTipDialog(mActivity,"提示","工单已成功提交，请耐心等待审核","确定");
                break;

        }
    }

    public void commitData(final boolean isCommit) {
        String name = mName.getText().toString().trim();
        String address = mAddress.getText().toString().trim();
        String leglePhone = mLegalPhone.getText().toString().trim();
        String legleNumber = mDocumentNumber.getText().toString().trim();
        String registName = mRegistName.getText().toString().trim();
        String registNumber = mRegistNumber.getText().toString().trim();
        String registAddress = mRegistAddress.getText().toString().trim();
        String contacts = mContacts.getText().toString().trim();
        String contactsPhone = mContactsPhone.getText().toString().trim();
        String typeStr = type;
        String style = mStyle.getText().toString().trim();
        String protocolNumber = mProtocolNumber.getText().toString().trim();
        String trendStr = trend;
        String note = mNote.getText().toString().trim();
//        if (TextUtils.isEmpty(name)) {
//            showToast("请填写商户名称");
//            return;
//        }
//        if (TextUtils.isEmpty(address)) {
//            showToast("请填写营业地址");
//            return;
//        }
//        if (TextUtils.isEmpty(leglePhone)) {
//            showToast("请填写联系电话");
//            return;
//        }
//        if (TextUtils.isEmpty(legleNumber)) {
//            showToast("请填写法人证件号码");
//            return;
//        }
//        if (TextUtils.isEmpty(registName)) {
//            showToast("请填写注册名称");
//            return;
//        }
//        if (TextUtils.isEmpty(registNumber)) {
//            showToast("请填写注册编号");
//            return;
//        }
//        if (TextUtils.isEmpty(registAddress)) {
//            showToast("请填写注册地址");
//            return;
//        }
//        if (TextUtils.isEmpty(contacts)) {
//            showToast("请填写网点联系人");
//            return;
//        }
//        if (TextUtils.isEmpty(contactsPhone)) {
//            showToast("请填写电话号码");
//            return;
//        }
////        if (TextUtils.isEmpty(name)){
////            showToast("请填写商户名称");
////            return;
////        }
////        if (TextUtils.isEmpty(name)){
////            showToast("请填写商户名称");
////            return;
////        }
//        if (TextUtils.isEmpty(style)) {
//            showToast("请填写行业类别");
//            return;
//        }
//        if (TextUtils.isEmpty(protocolNumber)) {
//            showToast("请填写协议编号");
//            return;
//        }
//        if (TextUtils.isEmpty(note)) {
//            showToast("请填写备注信息");
//            return;
//        }
//        if (heads.size() < 1) {
//            showToast("请上传门头图片");
//            return;
//        }
//        if (fronts.size() < 1) {
//            showToast("请上传前台图片");
//            return;
//        }
//        if (inners.size() < 1) {
//            showToast("请上传店内图片");
//            return;
//        }
//        if (businesss.size() < 1) {
//            showToast("请上传营业执照图片");
//            return;
//        }
//
//        if (legals.size() < 3) {
//            showToast("请上传身份证正反照");
//            return;
//        }
//        if (banks.size() < 3) {
//            showToast("请上传银行卡正反照");
//            return;
//        }
//        if (others.size() < 3) {
//            showToast("请上传2张其他照片");
//            return;
//        }
        DongZhiModle.addMerchantInfo(isCommit, name,id, address, leglePhone, legleNumber, registName, registNumber, registAddress, contacts, contactsPhone,style,
                typeStr, protocolNumber, trendStr, note,headUrl , frontUrl, innerUrl, businesUrl, legalUrl, bankUrl, otherUrl, new HttpCallBack<String>() {
                    @Override
                    public void success(String s) {
                        if (isCommit) {
                            DialogUtil.showTipDialog(mActivity, "提示", "工单已成功提交，请耐心等待审核", "确定");
                        } else {
                            DialogUtil.showTipDialog(mActivity, "提示", "信息保存成功", "确定");
                        }
                    }

                    @Override
                    public void fail(String errorStr) {

                    }
                });
    }


    /**
     * 计算GridView宽高
     *
     * @param gridView
     */
    public static void calGridViewWidthAndHeigh(int numColumns, GridView gridView) {

        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高

            if ((i + 1) % numColumns == 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }

            if ((i + 1) == len && (i + 1) % numColumns != 0) {
                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
            }
        }

        totalHeight += 40;

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridView.setLayoutParams(params);
    }


}
