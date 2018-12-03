package com.zhengyuan.twolevelnavigationbar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.zhengyuan.twolevelnavigationbar.fragment.FragmentAttention;
import com.zhengyuan.twolevelnavigationbar.fragment.FragmentDiscovery;
import com.zhengyuan.twolevelnavigationbar.fragment.FragmentHome;
import com.zhengyuan.twolevelnavigationbar.fragment.FragmentProfile;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private final String TAG = "MainActivity";
    private FragmentManager fragmentManager;//管理更新fragment;
    private FragmentTransaction fragmentTransaction;

    private BottomNavigationBar bottomNavigationBar;

    private FragmentProfile fragmentProfile;
    private FragmentAttention fragmentAttention;
    private FragmentHome fragmentHome;
    private FragmentDiscovery fragmentDiscovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        init();
    }

    private void init() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setInActiveColor(R.color.colorPrimaryDark);//文字图片的背景色
        bottomNavigationBar.setActiveColor(R.color.colorAccent);//bar背景颜色
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        numberBadgeItem.setText("9") //显示的文本
                .setBackgroundColor("#FF0000") //背景色
                .setTextColor("#FFFFFF") //文本颜色
                .setBorderColor("#000000") //border颜色
                .setBorderWidth(5) //border宽度px
                .setBackgroundColorResource(R.color.colorPrimaryDark) //背景色，资源文件获取
                .setBorderColorResource(R.color.colorPrimary) //border颜色，资源文件获取
                .setTextColorResource(R.color.colorAccent) //文本颜色，资源文件获取
                .setAnimationDuration(30) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.RIGHT | Gravity.TOP) //位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false


        //添加并设置图标、图标的颜色和文字
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_selector, "首页").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.tab_attention_selector, "爱好"))
                .addItem(new BottomNavigationItem(R.drawable.tab_discovery_selector, "发现"))
                .addItem(new BottomNavigationItem(R.drawable.tab_profile_selector, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    //设置初始界面
    private void setDefaultFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layFrame, FragmentHome.newInstance("首页"));
        fragmentTransaction.commit();
    }

    private void initFragment() {
        Log.i("test==", "首页== null");
        fragmentProfile = FragmentProfile.newInstance("我的");
        fragmentAttention = FragmentAttention.newInstance("爱好");
        fragmentHome = FragmentHome.newInstance("主页");
        fragmentDiscovery = FragmentDiscovery.newInstance("发现");

    }

    @Override
    public void onTabSelected(int position) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (fragmentHome == null) {
                    Log.i("test==", "首页== null");
                    fragmentHome = FragmentHome.newInstance("首页");
                }
                fragmentTransaction.replace(R.id.layFrame, fragmentHome);
                break;
            case 1:
                if (fragmentAttention == null) {
                    fragmentAttention = FragmentAttention.newInstance("爱好");
                }
                fragmentTransaction.replace(R.id.layFrame, fragmentAttention);
                break;
            case 2:
                if (fragmentDiscovery == null) {
                    fragmentDiscovery = FragmentDiscovery.newInstance("发现");
                }
                fragmentTransaction.replace(R.id.layFrame, fragmentDiscovery);
                break;
            case 3:
                if (fragmentProfile == null) {
                    fragmentProfile = FragmentProfile.newInstance("我的");
                }
                fragmentTransaction.replace(R.id.layFrame, fragmentProfile);
                break;
            default:
                break;
        }
        //事务提交
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }
}
