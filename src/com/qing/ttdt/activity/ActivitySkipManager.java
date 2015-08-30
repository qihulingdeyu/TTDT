package com.qing.ttdt.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class ActivitySkipManager {

    private static ActivitySkipManager instance = new ActivitySkipManager();
    private Intent intent;

    private ActivitySkipManager(){}

    public static ActivitySkipManager getInstance(){
        return instance;
    }
    //Activity跳转
    public void skipActivity(Activity activity, Class<?> clazz, Intent intent, boolean close){
        if(intent==null){
            this.intent = new Intent();
        }else{
            this.intent = intent;
        }
        this.intent.setClass(activity, clazz);
        activity.startActivity(this.intent);
        if(close){
            activity.finish();
        }
        this.intent = null;
    }
    //根据类名跳转
    private Class<?> clazz = null;
    public void skipActivity(Activity activity, String preActivityName, Intent intent, boolean close){
        try {
            Log.i("skipActivity","-----上一个Activity是------"+preActivityName);
//          preActivityName = preActivityName.substring(0, preActivityName.length()-2);
            clazz = Class.forName(preActivityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(intent==null){
            this.intent = new Intent();
        }else{
            this.intent = intent;
        }
        this.intent.setClass(activity, clazz);
        activity.startActivity(this.intent);
        if(close){
            activity.finish();
        }
        this.intent = null;
        clazz = null;
    }

}
