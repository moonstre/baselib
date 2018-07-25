package com.choucheng.dongzhibot.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bjkj.library.okhttp.HttpCallBack;
import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.adapter.CommonAdapter;
import com.choucheng.dongzhibot.adapter.CommonViewHolder;
import com.choucheng.dongzhibot.base.BaseActivity;
import com.choucheng.dongzhibot.bean.InstallOrderBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.choucheng.dongzhibot.view.DialogUtil;
import com.choucheng.dongzhibot.view.RCRelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/6/13.
 * 装机工单
 */

public class InstallOrderActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;
    ArrayList<InstallOrderBean.InstallOrder.InstallOrderItem> datas=new ArrayList();
    CommonAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_install_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        super.initData();
//        InstallOrderBean.InstallOrder.InstallOrderItem item1=new InstallOrderBean.InstallOrder.InstallOrderItem();
//        item1.name="账号1";
//        item1.address="武汉市江夏区";
//        item1.status="0";
//
//        InstallOrderBean.InstallOrder.InstallOrderItem item2=new InstallOrderBean.InstallOrder.InstallOrderItem();
//        item2.name="账号2";
//        item2.address="武汉市江夏区";
//        item2.status="1";
//
//        InstallOrderBean.InstallOrder.InstallOrderItem item3=new InstallOrderBean.InstallOrder.InstallOrderItem();
//        item3.name="账号3";
//        item3.address="武汉市江夏区";
//        item3.status="2";
//
//        InstallOrderBean.InstallOrder.InstallOrderItem item4=new InstallOrderBean.InstallOrder.InstallOrderItem();
//        item4.name="账号4";
//        item4.address="武汉市江夏区";
//        item4.status="3";
//
//        InstallOrderBean.InstallOrder.InstallOrderItem item5=new InstallOrderBean.InstallOrder.InstallOrderItem();
//        item5.name="账号5";
//        item5.address="武汉市江夏区";
//        item5.status="4";
//        datas.add(item1);
//        datas.add(item2);
//        datas.add(item3);
//        datas.add(item4);
//        datas.add(item5);
        adapter = new CommonAdapter<InstallOrderBean.InstallOrder.InstallOrderItem>(mActivity,datas,R.layout.item_install_order) {
            @Override
            protected void convertView(int position, View item,final InstallOrderBean.InstallOrder.InstallOrderItem installOrderItem) {
                TextView name = (TextView) CommonViewHolder.get(item, R.id.name);
                TextView address = (TextView) CommonViewHolder.get(item, R.id.address);
                TextView status = (TextView) CommonViewHolder.get(item, R.id.status);
                RCRelativeLayout status_bg = (RCRelativeLayout) CommonViewHolder.get(item, R.id.status_bg);
                name.setText("商户名称："+installOrderItem.name);
                address.setText("地址："+installOrderItem.address);

//                is_over 0未接受1已接受2拒绝接受3装机中4装机完成
                if (0==(Integer.parseInt(installOrderItem.is_over))){
                    status.setText("待接受");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_green));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_green));
                    status_bg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Dialog dialog = DialogUtil.showOrderDialog(mActivity, installOrderItem.name, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    dialog.dismiss();
                                    DongZhiModle.installOrderAccept(installOrderItem.merchant_id,installOrderItem.device_id,"1",new HttpCallBack<String>(){
                                        @Override
                                        public void success(String s) {
                                            Toast.makeText(InstallOrderActivity.this,"接受成功",Toast.LENGTH_SHORT).show();
                                            getData();
                                        }

                                        @Override
                                        public void fail(String errorStr) {
                                            Toast.makeText(InstallOrderActivity.this,"接受失败",Toast.LENGTH_SHORT).show();
                                            getData();
                                        }
                                    });
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    DongZhiModle.installOrderAccept(installOrderItem.merchant_id,installOrderItem.device_id,"2",new HttpCallBack<String>(){
                                        @Override
                                        public void success(String s) {
                                            Toast.makeText(InstallOrderActivity.this,"拒绝成功",Toast.LENGTH_SHORT).show();
                                            getData();
                                        }

                                        @Override
                                        public void fail(String errorStr) {
                                            Toast.makeText(InstallOrderActivity.this,"拒绝失败",Toast.LENGTH_SHORT).show();
                                            getData();
                                        }
                                    });

                                }
                            });
                        }
                    });
                }else {
                    if ("0".equals(installOrderItem.status)){
                        status.setText("未审核");
                        status.setTextColor(mActivity.getResources().getColor(R.color.main_yellow));
                        status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_yellow));
                    }else if ("1".equals(installOrderItem.status)){
                        status.setText("审核中");
                        status.setTextColor(mActivity.getResources().getColor(R.color.main_red));
                        status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_red));
                    }else if ("2".equals(installOrderItem.status)){
                        status.setText("已审核 ");
                        status.setTextColor(mActivity.getResources().getColor(R.color.main_blue));
                        status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_blue));
                    }else if ("3".equals(installOrderItem.status)){
                        status.setText("审核不通过");
                        status.setTextColor(mActivity.getResources().getColor(R.color.main_purple));
                        status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_purple));
                    }
                }

            }
        };

        listView.setAdapter(adapter);
        getData();
    }


    private void getData(){
        DongZhiModle.installOrder(new HttpCallBack<ArrayList<InstallOrderBean.InstallOrder.InstallOrderItem>>() {
            @Override
            public void success(ArrayList<InstallOrderBean.InstallOrder.InstallOrderItem> installOrderItems) {
                datas.clear();
                datas.addAll(installOrderItems);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String errorStr) {

            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mActivity,SeeInstallOrderActivity.class);
                intent.putExtra("merchant_id",datas.get(i).merchant_id);
                intent.putExtra("device_id",datas.get(i).device_id);
                    startActivity(intent);
            }
        });
    }
}
