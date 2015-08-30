package com.qing.ttdt.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.qing.ttdt.R;
import com.qing.ttdt.activity.HomePageActivity;
import com.qing.ttdt.domain.MusicInfo;

public class PlayNotify {

    private Context context;
    private NotificationManager notifyManager;
    
    public PlayNotify(Context context, MusicInfo info){
        this.context = context;
        //添加通知服务
        notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notify(info.getTitle(), info.getArtist());
    }
    
    /**
       * http://blog.csdn.net/xxbs2003/article/details/19167331
       * @param name
       */
    private void notify(String songName, String singerName) {
        Intent intent = new Intent();
        //点击通知栏后要跳转到哪个activity
        intent.setClass(context, HomePageActivity.class);
        //一定要有Intent.FLAG_ACTIVITY_NEW_TASK
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        //PendingIntent 是Intent的包装类  
      /**
       * (1) android.app.PendingIntent.FLAG_UPDATE_CURRENT 如果PendingIntent已经存在，保留它并且只替换它的extra数据。
       * (2) android.app.PendingIntent.FLAG_CANCEL_CURRENT 如果PendingIntent已经存在，那么当前的PendingIntent会取消掉，然后产生一个新的PendingIntent。
       * (3) android.app.PendingIntent.FLAG_ONE_SHOT PendingIntent只能使用一次。调用了实例方法send()之后，它会被自动cancel掉，再次调用send()方法将失败。
       * (4) android.app.PendingIntent.FLAG_NO_CREATE 如果PendingIntent不存在，简单了当返回null。
       * 
       */
        PendingIntent contentIntent = PendingIntent.getActivity(context, 22, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        
        
        //创建一个通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //设置点击后状态栏显示的内容
        builder.setTicker("正在播放 "+songName);
        //builder.set
        builder.setContentIntent(contentIntent);
        
        /**
       * 常量：
        DEFAULT_ALL    使用所有默认值，比如声音，震动，闪屏等等
        DEFAULT_LIGHTS 使用默认闪光提示
        DEFAULT_SOUNDS 使用默认提示声音
        DEFAULT_VIBRATE 使用默认手机震动 
        【说明】：加入手机震动，一定要在manifest.xml中加入权限：
        <uses-permission android:name="android.permission.VIBRATE" />
       */
        //设置通知栏显示的内容及效果
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(songName);
        builder.setContentText(singerName);
        builder.setDefaults(Notification.DEFAULT_ALL);
        //将设置的内容加入通知中
        Notification notify = builder.build();
        //Notification noti = new Notification(R.drawable.statbar_default_icon, "正在播放 ", System.currentTimeMillis());
        
        /**
       * FLAG_AUTO_CANCEL：
        设置此Flag，当用户点击时，通知会被清除。
        FLAG_NO_CLEAR：
        设置此Flag，当用户点击清除所有按钮时，该通知不被清除。
        FLAG_FOREGROUND_SERVICE：
        设置此Flag，表示该通知为正运行的服务（当你退出手机QQ时，在状态栏会看到一个QQ图标）
       */
        //设置点击通知栏的清除按钮时，不被清除掉
        //notify.flags = Notification.FLAG_NO_CLEAR;
        
        //将通知加入通知管理器中
        notifyManager.notify(22, notify);
    }
}
