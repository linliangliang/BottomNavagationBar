package com.zhengyuan.bottomnavigationbar;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.zhengyuan.bottomnavigationbar.fragment.ContactsFragment;
import com.zhengyuan.bottomnavigationbar.fragment.DongtaiFragment;
import com.zhengyuan.bottomnavigationbar.fragment.MsgFragment;

import java.util.ArrayList;

import static com.ashokvarma.bottomnavigation.ShapeBadgeItem.SHAPE_OVAL;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;

    private MsgFragment msgFragment;
    private ContactsFragment contactsFragment;
    private DongtaiFragment dongtaiFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        init();
    }

    private void init() {
        /*要先设计模式后再添加图标！
        设置按钮模式  MODE_FIXED表示固定   MODE_SHIFTING表示转移
        mode_default:如果选项大于3个，使用mode_shifting，否则使用mode_fixed
        mode_fixed:每个item对应名称，不选中也会显示
        mode_shifting:每个item对应名称，只有选中才会显示，不选中隐藏
        mode_fixed_no_title:相当于mode_fixed只是不显示所有文字
        mode_shifting_no_title:相当于mode_shifting只是不显示所有文字*/
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        /*设置背景风格
         BACKGROUND_STYLE_STATIC表示静态的
        BACKGROUND_STYLE_RIPPLE表示涟漪的，也就是可以变化的 ，跟随setActiveColor里面的颜色变化*/
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
       /* in-active color：图标和文本未被激活或选中的颜色；默认颜色为Theme’s Primary Color
        active color : 在BACKGROUND_STYLE_STATIC下，为图标和文本激活或选中的颜色；在BACKGROUND_STYLE_RIPPLE下，为整个控件的背景颜色；默认颜色为Color.LTGRAY
        background color :在BACKGROUND_STYLE_STATIC 下，为整个空控件的背景色；在 BACKGROUND_STYLE_RIPPLE 下为图标和文本被激活或选中的颜色；默认颜色为Color.WHITE
        */
        bottomNavigationBar.setInActiveColor(R.color.colorPrimaryDark);//文字图片的背景色
        bottomNavigationBar.setActiveColor(R.color.blue);//bar背景颜色


        /*其中item可以左右上角的数字：*/
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem();
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

        shapeBadgeItem.setShape(SHAPE_OVAL) //形状
                .setShapeColor(Color.BLUE) //颜色
                .setShapeColorResource(R.color.colorPrimaryDark) //颜色，资源文件获取
                .setEdgeMarginInDp(this, 0) //距离Item的margin，dp
                .setEdgeMarginInPixels(20) //距离Item的margin，px
                .setSizeInDp(this, 10, 10) //宽高，dp
                .setSizeInPixels(20, 20) //宽高，px
                .setAnimationDuration(200) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.LEFT) //位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false

        //添加并设置图标、图标的颜色和文字
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_attention_selector, "消息").setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.tab_discovery_selector, "联系人").setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.tab_profile_selector, "动态"))
                .addItem(new BottomNavigationItem(R.drawable.tab_profile_selector, "动态2"))
                .addItem(new BottomNavigationItem(R.drawable.tab_profile_selector, "动态2"))
                .setFirstSelectedPosition(1)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    //设置初始界面
    private void setDefaultFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layFrame, MsgFragment.newInstance("消息"));
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (msgFragment == null) {
                    msgFragment = MsgFragment.newInstance("消息");
                }
                transaction.replace(R.id.layFrame, msgFragment);
                break;
            case 1:
                if (contactsFragment == null) {
                    contactsFragment = ContactsFragment.newInstance("联系人");
                }
                transaction.replace(R.id.layFrame, contactsFragment);
                break;
            case 2:
                if (dongtaiFragment == null) {
                    dongtaiFragment = DongtaiFragment.newInstance("动态");
                }
                transaction.replace(R.id.layFrame, dongtaiFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("dongtaiFragment", "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }

}
