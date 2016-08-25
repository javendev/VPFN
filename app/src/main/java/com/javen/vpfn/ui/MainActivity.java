package com.javen.vpfn.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.javen.vpfn.R;
import com.javen.vpfn.ui.fragment.FragmentFour;
import com.javen.vpfn.ui.fragment.FragmentOne;
import com.javen.vpfn.ui.fragment.FragmentThree;
import com.javen.vpfn.ui.fragment.FragmentTwo;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_ViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.radio_joke)
    RadioButton radioJoke;
    @BindView(R.id.radio_girls)
    RadioButton radioGirls;
    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_about)
    RadioButton radioAbout;
    @BindView(R.id.main_tab_RadioGroup)
    RadioGroup mainTabRadioGroup;

    //类型为Fragment的动态数组
    private ArrayList<Fragment> fragmentList;
    Fragment oneFragment, twoFragment, threeFragment, fourFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvents(); //获取控件
        initViewPager();//ViewPager初始化函数
    }

    private void initEvents() {
        mainTabRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initViewPager() {
        fragmentList = new ArrayList<Fragment>();

        oneFragment = new FragmentOne();
        twoFragment = new FragmentTwo();
        threeFragment = new FragmentThree();
        fourFragment = new FragmentFour();

        //将各Fragment加入数组中
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);
        //关闭预加载，默认一次只加载一个Fragment
        mainViewPager.setOffscreenPageLimit(1);
        //设置ViewPager的设配器
        mainViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragmentList));
        //当前为第一个页面
        mainViewPager.setCurrentItem(0);
        //ViewPager的页面改变监听器
        mainViewPager.addOnPageChangeListener(new MyViewPageListner());
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (oneFragment != null && !oneFragment.isHidden()) fragmentTransaction.hide(oneFragment);
        if (twoFragment != null && !twoFragment.isHidden()) fragmentTransaction.hide(twoFragment);
        if (threeFragment != null && !threeFragment.isHidden())
            fragmentTransaction.hide(threeFragment);
        if (fourFragment != null && !fourFragment.isHidden())
            fragmentTransaction.hide(fourFragment);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public class MyViewPageListner implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(fTransaction);
            //获取当前页面用于改变对应RadioButton的状态
            int current = mainViewPager.getCurrentItem();
            switch (current) {
                case 0:
                    mainTabRadioGroup.check(R.id.radio_joke);
                    if (oneFragment.isHidden())
                        fTransaction.show(oneFragment);
                    break;
                case 1:
                    mainTabRadioGroup.check(R.id.radio_girls);
                    if (twoFragment.isHidden())
                        fTransaction.show(twoFragment);
                    break;
                case 2:
                    mainTabRadioGroup.check(R.id.radio_news);
                    if (threeFragment.isHidden())
                        fTransaction.show(threeFragment);
                    break;
                case 3:
                    mainTabRadioGroup.check(R.id.radio_about);
                    if (fourFragment.isHidden())
                        fTransaction.show(fourFragment);
                    break;
            }
            fTransaction.commit();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //获取当前被选中的RadioButton的ID，用于改变ViewPager的当前页
        int current = 0;
        switch (checkedId) {
            case R.id.radio_joke:
                current = 0;
                break;
            case R.id.radio_girls:
                current = 1;
                break;
            case R.id.radio_news:
                current = 2;
                break;
            case R.id.radio_about:
                current = 3;
                break;
        }
        if (mainViewPager.getCurrentItem() != current) {
            mainViewPager.setCurrentItem(current);
        }
    }
}
