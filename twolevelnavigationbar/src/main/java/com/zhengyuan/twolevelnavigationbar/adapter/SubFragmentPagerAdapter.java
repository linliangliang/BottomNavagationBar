package com.zhengyuan.twolevelnavigationbar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhengyuan.twolevelnavigationbar.subfragment.FragmentSubFirst;
import com.zhengyuan.twolevelnavigationbar.subfragment.FragmentSubSecond;
import com.zhengyuan.twolevelnavigationbar.subfragment.FragmentSubThird;

/**
 * Created by 林亮 on 2018/11/30
 */
public class SubFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"爱好", "发现", "主页"};

    public SubFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentSubFirst();
        } else if (position == 1) {
            return new FragmentSubSecond();
        }
        return new FragmentSubThird();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}