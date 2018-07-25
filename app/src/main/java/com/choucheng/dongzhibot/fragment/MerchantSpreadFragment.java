package com.choucheng.dongzhibot.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by admin on 2018/7/21.
 */

public class MerchantSpreadFragment extends BaseFragment {
    @Bind(R.id.totle)
    TextView totle;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_merchant_spread;
    }

    @Override
    public void initView() {

    }

}
