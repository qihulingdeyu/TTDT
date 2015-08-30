package com.qing.ttdt.service;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

import com.qing.ttdt.domain.MusicInfo;

public class MusicPlayService extends Service {
    
    private final String TAG = "MusicPlayService";
    public static MediaPlayer player;
    private ArrayList<MusicInfo> musicList;
    private static int curIndex;
    private String flag = "free";
    private MusicInfo musicInfo = null;
    public static boolean isChange = false;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });
        
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        player = null;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicList = (ArrayList<MusicInfo>) intent.getSerializableExtra("musicList");
        if(musicList!=null){
        curIndex = intent.getIntExtra("index", 0);
        flag = intent.getStringExtra("flag");
        
        Log.i(TAG,"--"+curIndex+"--flag--"+flag);
        start(flag);
        }
        return super.onStartCommand(intent, flags, startId);
    }
    
    public static int getCurIndex() {
        return curIndex;
    }
    private void start(String flag) {
        if(flag.equals("free")){
            musicInfo = musicList.get(curIndex);
            prepare(musicInfo.getPath());
            play();
        }else if(flag.equals("prepare")){
            musicInfo = musicList.get(curIndex);
            prepare(musicInfo.getPath());
        }else if(flag.equals("preSong")){
            preSong();
        }else if(flag.equals("play")){
            pause();
        }else if(flag.equals("pause")){
            play();
        }else if(flag.equals("nextSong")){
            nextSong();
        }
    }

    private void prepare(String path) {
        isChange = true;
        player.reset();
        try {
            player.setDataSource(path);
            player.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void preSong(){
        curIndex--;
        if(curIndex < 0)
            curIndex = musicList.size()-1;
        start("free");
    }
    private void play(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isChange = false;
            }
        }).start();
        player.start();
        flag = "play";
    }
    private void pause(){
        player.pause();
        flag = "pause";
    }
    private void nextSong(){
        curIndex++;
        if(curIndex >= musicList.size())
            curIndex = 0;
        start("free");
    }
}
