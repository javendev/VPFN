package com.javen.vpfn.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.javen.vpfn.R;
import com.javen.vpfn.common.DialogUtils;
import com.orhanobut.logger.Logger;

/**
 * Created by Javen on 16/8/25.
 */
public class FragmentTwo extends BaseFragment {
    public FragmentTwo() {
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Logger.i("FragmentTwo onCreateView");
        return view;
    }

    @Override
    public void loadData() {
        Logger.i("FragmentTwo loadData");
        DialogUtils.progressDialog(mActivity,"FragmentTwo loading...");

    }

}
