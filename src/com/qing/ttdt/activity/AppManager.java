package com.qing.ttdt.activity;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

public class AppManager {

    private static AppManager instance;
    private static Stack<Activity> activityStack;

    private AppManager(){}

    //获取实例
    public static AppManager getAppManager(){
        if(instance==null){
            instance = new AppManager();
        }
        return instance;
    }

    //添加到栈中
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    //最后压入的
    public Activity currentActivity(){
        return activityStack.lastElement();
    }

    //结束最后压入的
    public void finishActivity(){
        Activity activity = activityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activity = null;
        }
    }

    //结束指定的Activity
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    //结束指定类的Activity
    public void finishActivity(Class<?> classname){
        for(Activity activity : activityStack){
            if(activity.getClass().equals(classname)){
                finishActivity(activity);
            }
        }
    }

    //结束所有Activity
    public void finishAllActivity(){
        int size = activityStack.size();
        for(int i=0;i<size;i++){
            if(activityStack.get(i)!=null){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    //App退出
    public void appExit(Context context){
        try{
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            //使用时必须在androidmanifest.xml文件中加入KILL_BACKGROUND_PROCESSES这个权限
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        }catch(Exception e){
        }
    }
}
