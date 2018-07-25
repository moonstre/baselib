package com.choucheng.dongzhibot.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choucheng.dongzhibot.R;
import com.choucheng.dongzhibot.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/7/21.
 */

public class InstallOrderFragment extends BaseFragment {
    @Bind(R.id.totle)
    TextView totle;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_install_order;
    }

    @Override
    public void initView() {

    }

}
