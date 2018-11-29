package com.zhengyuan.bottomnaviagtionbar;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhengyuan.bottomnaviagtionbar.adapter.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static final int[] mTabRes = new int[]{R.drawable.tab_home_selector, R.drawable.tab_discovery_selector, R.drawable.tab_attention_selector, R.drawable.tab_profile_selector};
    public static final int[] mTabResPressed = new int[]{R.drawable.ic_tab_strip_icon_feed_selected, R.drawable.ic_tab_strip_icon_category_selected, R.drawable.ic_tab_strip_icon_pgc_selected, R.drawable.ic_tab_strip_icon_profile_selected};


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initViews();
    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(mTabResPressed[0]);
        two.setIcon(mTabRes[1]);
        three.setIcon(mTabRes[2]);
        four.setIcon(mTabRes[3]);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //改变Tab 状态
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    if (i == tab.getPosition()) {
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(mTabResPressed[i]));
                    } else {
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(mTabRes[i]));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
