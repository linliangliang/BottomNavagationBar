package com.zhengyuan.bottomnaviagtionbar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhengyuan.bottomnaviagtionbar.fragment.AttentionFragment;
import com.zhengyuan.bottomnaviagtionbar.fragment.DiscoveryFragment;
import com.zhengyuan.bottomnaviagtionbar.fragment.HomeFragment;
import com.zhengyuan.bottomnaviagtionbar.fragment.ProfileFragment;

/**
 * Created by 林亮 on 2018/11/29
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"爱好", "发现", "主页", "我的"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AttentionFragment();
        } else if (position == 1) {
            return new DiscoveryFragment();
        } else if (position == 2) {
            return new HomeFragment();
        }
        return new ProfileFragment();
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