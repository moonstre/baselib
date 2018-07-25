package com.choucheng.dongzhibot.activity;

import android.app.Dialog;
import android.content.Intent;
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
import com.choucheng.dongzhibot.bean.ProtectOrderBean;
import com.choucheng.dongzhibot.modle.DongZhiModle;
import com.choucheng.dongzhibot.view.DialogUtil;
import com.choucheng.dongzhibot.view.RCRelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by admin on 2018/6/13.
 * 维护工单列表
 */

public class ProtectOrderActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;
    ArrayList<ProtectOrderBean.ProtectOrder.ProtectOrderItem> datas = new ArrayList();
    CommonAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_protect_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        super.initData();
        adapter = new CommonAdapter<ProtectOrderBean.ProtectOrder.ProtectOrderItem>(mActivity, datas, R.layout.item_install_order) {
            @Override
            protected void convertView(int position, View item, final ProtectOrderBean.ProtectOrder.ProtectOrderItem installOrderItem) {
                TextView name = (TextView) CommonViewHolder.get(item, R.id.name);
                TextView address = (TextView) CommonViewHolder.get(item, R.id.address);
                TextView status = (TextView) CommonViewHolder.get(item, R.id.status);
                RCRelativeLayout status_bg = (RCRelativeLayout) CommonViewHolder.get(item, R.id.status_bg);
                name.setText("商户名称：" + installOrderItem.merchant_info.name);
                address.setText("地址：" + installOrderItem.merchant_info.address);

//                is_over 0未接受1已接受2拒绝接受3装机中4装机完成
                if ("0".equals(installOrderItem.status)) {
                    status.setText("待接受");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_green));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_green));
                    status_bg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Dialog dialog = DialogUtil.showOrderDialog(mActivity, installOrderItem.merchant_info.address, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    dialog.dismiss();
                                    DongZhiModle.protectOrderAccept(installOrderItem.d_id, "1", new HttpCallBack<String>() {
                                        @Override
                                        public void success(String s) {
                                            Toast.makeText(ProtectOrderActivity.this, "接受成功", Toast.LENGTH_SHORT).show();
                                            getData();
                                        }

                                        @Override
                                        public void fail(String errorStr) {
                                            Toast.makeText(ProtectOrderActivity.this, "接受失败", Toast.LENGTH_SHORT).show();
                                            getData();
                                        }
                                    });
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    DongZhiModle.protectOrderAccept(installOrderItem.d_id, "2", new HttpCallBack<String>() {
                                        @Override
                                        public void success(String s) {
                                            Toast.makeText(ProtectOrderActivity.this, "拒绝成功", Toast.LENGTH_SHORT).show();
                                            getData();
                                        }

                                        @Override
                                        public void fail(String errorStr) {
                                            Toast.makeText(ProtectOrderActivity.this, "拒绝失败", Toast.LENGTH_SHORT).show();
                                            getData();
                                        }
                                    });

                                }
                            });
                        }
                    });
                } else if ("1".equals(installOrderItem.status)) {
                    status.setText("待审核");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_yellow));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_yellow));
                } else if ("2".equals(installOrderItem.status)) {
                    status.setText("未通过");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_red));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_red));
                } else if ("3".equals(installOrderItem.status)) {
                    status.setText("进行中 ");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_blue));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_blue));
                } else if ("4".equals(installOrderItem.status)) {
                    status.setText("装机完成");
                    status.setTextColor(mActivity.getResources().getColor(R.color.main_purple));
                    status_bg.setStrokeColor(mActivity.getResources().getColor(R.color.main_purple));
                }
            }
        };

        listView.setAdapter(adapter);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        DongZhiModle.protectOrder(new HttpCallBack<ArrayList<ProtectOrderBean.ProtectOrder.ProtectOrderItem>>() {
            @Override
            public void success(ArrayList<ProtectOrderBean.ProtectOrder.ProtectOrderItem> installOrderItems) {
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
                Intent intent = new Intent(mActivity, ProtectOrderInfoActivity.class);
                intent.putExtra("d_id", datas.get(i).d_id);
                intent.putExtra("xunjianid", datas.get(i).xunjianid);
                startActivity(intent);
            }
        });
    }
}
