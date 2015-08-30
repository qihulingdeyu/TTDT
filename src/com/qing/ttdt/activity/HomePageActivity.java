package com.qing.ttdt.activity;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.qing.ttdt.R;

@SuppressWarnings("deprecation")
public class HomePageActivity extends ActivityGroup {
    private Context context = HomePageActivity.this;
    private TabHost tabhost;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // 获取tabhost并初始化
        initTabhost();
        
        // 手势的监听
        gestureDetector = new GestureDetector(this,
                new MyGestureDetectorListener());

    }

    private View tab;

    private View getTab(int tabText) {
        tab = LayoutInflater.from(context).inflate(
                R.layout.homepage_tab, null);
        ((TextView) tab.findViewById(R.id.homepage_tab_txt)).setText(getResources().getString(tabText));
        return tab;
    }

    private void initTabhost() {
        tabhost = (TabHost) findViewById(android.R.id.tabhost);

        tabhost.setup(getLocalActivityManager());
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator(getTab(R.string.my))
                .setContent(new Intent(this, TabMyActivity.class)));
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator(getTab(R.string.findMusic))
                .setContent(new Intent(this, TabFindMusicActivity.class)));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator(getTab(R.string.search))
                .setContent(new Intent(this, TabSearchActivity.class)));
        tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator(getTab(R.string.recommend))
                .setContent(new Intent(this, TabRecommendActivity.class)));
        // 默认显示第一个
        tabhost.setCurrentTab(0);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (gestureDetector!=null && gestureDetector.onTouchEvent(event)) {//分派触摸事件
            event.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(event);
    }
    
    private static final int SLIDE_MIN_WIDTH = 120;
    private static final int SLIDE_MAX_HEIGHT = 250;
    private static final int SLIDE_THRESHOLD_VELOCITY = 200;//极限速率
    private static int maxTabIndex = 3;
    private int currentTabIndex = 0;



    class MyGestureDetectorListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            try {
                //e1:起始位置，e2:终点位置
                if (Math.abs(e1.getY() - e2.getY()) > SLIDE_MAX_HEIGHT)
                    return false;
                // right to left swipe
                if (e1.getX() - e2.getX() > SLIDE_MIN_WIDTH
                        && Math.abs(velocityX) > SLIDE_THRESHOLD_VELOCITY) {
                    Log.i("test", "right--->left");
                    if (currentTabIndex < maxTabIndex) {
                        currentTabIndex++;
                    }
                    tabhost.setCurrentTab(currentTabIndex);
                } else if (e2.getX() - e1.getX() > SLIDE_MIN_WIDTH
                        && Math.abs(velocityX) > SLIDE_THRESHOLD_VELOCITY) {
                    Log.i("test", "left--->right");
                    if (currentTabIndex > 0) {
                        currentTabIndex--;
                    }
                    tabhost.setCurrentTab(currentTabIndex);
                }
            } catch (Exception e) {
            }
            return false;
        }
    }
}
