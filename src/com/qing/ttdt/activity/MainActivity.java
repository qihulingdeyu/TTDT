package com.qing.ttdt.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;

import com.qing.ttdt.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                skipActivity(mActivity, HomePageActivity.class, null, true);
            }
        }, 1500);
        
//        Button start = (Button) findViewById(R.id.start);
//        start.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                skipActivity(currentActivity, HomePageActivity.class, null, true);
//            }
//        });
    }

}
