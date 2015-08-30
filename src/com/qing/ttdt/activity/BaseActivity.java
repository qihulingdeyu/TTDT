package com.qing.ttdt.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.qing.ttdt.R;

import java.util.Timer;
import java.util.TimerTask;

public class BaseActivity extends Activity {

    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
    }

    //Activity跳转
    public void skipActivity(Activity activity, Class<?> clazz, Intent intent, boolean close){
        Intent mIntent = null;
        if(intent==null){
            mIntent = new Intent();
        }else{
            mIntent = intent;
        }
        mIntent.setClass(activity, clazz);
        activity.startActivity(mIntent);
        if(close){
            activity.finish();
        }
        mIntent = null;
    }

    public void skipActivity(Class<?> clazz, Intent intent, boolean close){
        Intent mIntent = null;
        if(intent==null){
            mIntent = new Intent();
        }else{
            mIntent = intent;
        }
        mIntent.setClass(mActivity, clazz);
        mActivity.startActivity(mIntent);
        if(close){
            mActivity.finish();
        }
        mIntent = null;
    }

    //根据类名跳转
    public void skipActivity(Activity activity, String preActivityName, Intent intent, boolean close){
        Class<?> clazz = null;
        try {
            Log.i(TAG, "-----上一个Activity是------" + preActivityName);
//          preActivityName = preActivityName.substring(0, preActivityName.length()-2);
            clazz = Class.forName(preActivityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        skipActivity(activity, clazz, intent, close);
        clazz = null;
    }

    public void skipActivity(String preActivityName, Intent intent, boolean close){
        Class<?> clazz = null;
        try {
            Log.i(TAG, "-----上一个Activity是------" + preActivityName);
//          preActivityName = preActivityName.substring(0, preActivityName.length()-2);
            clazz = Class.forName(preActivityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        skipActivity(clazz,intent,close);
        clazz = null;
    }

    public void showToast(String str){
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }
}
