package com.javen.vpfn.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment {
    public Context mContext;
    public FragmentActivity mActivity;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted;

    /**
     * 此方法可以得到上下文对象
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /*
     * 返回一个需要展示的View
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mContext=mActivity.getApplicationContext();
        View view = initView(inflater,container);
        initFindViewById(view);
        isViewCreated = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            loadData();
        }
    }
    /*
    * 当Activity初始化之后可以在这里进行一些数据的初始化操作
    */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            loadData();
        }
    }
    /**
     * 子类可以复写此方法初始化事件
     */
    protected  void initEvent(){
    }
    /**
     * 子类实现此抽象方法返回View进行展示
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化控件
     */
    protected void initFindViewById(View view){

    }

    /**
     * 子类实现延迟加载数据的方法
     */
    public abstract  void loadData();

}