package com.qing.ttdt.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.activity.ActivitySkipManager;
import com.qing.ttdt.activity.HomePageActivity;
import com.qing.ttdt.activity.MusicPlayActivity;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.service.MusicPlayService;
import com.qing.ttdt.utils.MusicInfoUtils;

public class BottomMusicPlayListener implements OnClickListener,OnCheckedChangeListener{
    
    private final String TAG = "BottomMusicPlayListener";
    private ActivitySkipManager skipManager = ActivitySkipManager.getInstance();
    private Activity activity;
    private ArrayList<MusicInfo> musicList;
    private int oldIndex;
    private int curIndex;
    private MusicInfo musicInfo;
    private ProgressBar playProgressBar;
    private ImageButton bottom_singer_icon;
    private TextView bottom_singer_name;
    private TextView bottom_music_name;
    private TextView bottom_music_playtime;
    private TextView bottom_music_duartion;
    private CheckBox bottom_music_play_pause_btn;
    private ImageButton bottom_music_nextsong_btn;
    private ImageButton bottom_music_menu_btn;
    
    private Handler handler = new Handler(new Handler.Callback() {
        private MusicInfo info;
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==21){
                playProgressBar.setProgress(msg.arg1);
                bottom_music_playtime.setText(MusicInfoUtils.formatDuration(msg.arg1));
            }else if(msg.what==22){
                info = (MusicInfo) msg.obj;
                playProgressBar.setMax(msg.arg1);
                bottom_singer_name.setText(info.getArtist());
                bottom_music_name.setText(info.getTitle());
                bottom_music_playtime.setText("00:00");
                bottom_music_duartion.setText(" - "+MusicInfoUtils.formatDuration(info.getDuration()));
//                if(curIndex!=oldIndex)
                    bottom_music_play_pause_btn.setChecked(true);
            }
            return false;
        }
    });
    
    private Message msg = null;
    public BottomMusicPlayListener(Activity activity, ArrayList<MusicInfo> musicList, int index){
        this.activity = activity;
        this.musicList = musicList;
        this.oldIndex = index;
        this.curIndex = index;
        this.musicInfo = ((musicList==null || musicList.size()==0) ? null:musicList.get(index));
        init();
    }
    
    public void init(){
        playProgressBar = (ProgressBar) activity.findViewById(R.id.playProgressBar);
        
        bottom_singer_icon = (ImageButton) activity.findViewById(R.id.bottom_singer_icon);
        bottom_singer_icon.setOnClickListener(this);
        
        bottom_singer_name = (TextView) activity.findViewById(R.id.bottom_singer_name);
        bottom_music_name = (TextView) activity.findViewById(R.id.bottom_music_name);
        bottom_music_playtime = (TextView) activity.findViewById(R.id.bottom_music_playtime);
        bottom_music_duartion = (TextView) activity.findViewById(R.id.bottom_music_duartion);
        
        bottom_music_play_pause_btn = (CheckBox) activity.findViewById(R.id.bottom_music_play_pause_btn);
        bottom_music_play_pause_btn.setOnCheckedChangeListener(this);
        
        bottom_music_nextsong_btn = (ImageButton) activity.findViewById(R.id.bottom_music_nextsong_btn);
        bottom_music_nextsong_btn.setOnClickListener(this);
        
        bottom_music_menu_btn = (ImageButton) activity.findViewById(R.id.bottom_music_menu_btn);
        bottom_music_menu_btn.setOnClickListener(this);

        changeListener();
        
        if(musicInfo!=null){
            msg = new Message();
            msg.what = 22;
            msg.obj = musicInfo;
            handler.sendMessage(msg);
            
            startMusicService(curIndex, "prepare");
        }
    }
    
    /*
     * 监听音乐是否变化
     */
    public void changeListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer player = null;
                Message msg = null;
//                while(true){
//                    if(player==null){
//                        player = MusicPlayService.player;
//                    }else{
//                        //更新进度条
//                        if(player.isPlaying()){
//                            //设置播放进度
//                            msg = new Message();
//                            msg.what = 21;
//                            msg.arg1 = player.getCurrentPosition();
//                            handler.sendMessage(msg);
//                            try {//延迟更新
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        //更新其它UI
//                        if(MusicPlayService.isChange){
//                            curIndex = MusicPlayService.getCurIndex();
//                            msg = new Message();
//                            msg.what = 22;
//                            msg.arg1 = player.getDuration();
//                            msg.obj = musicList.get(curIndex);
//                            handler.sendMessage(msg);
//                        }
//                    }
//                }
            }
        }).start();
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bottom_singer_icon:
            //专辑图片
            Log.i("TAG",HomePageActivity.class.getName());
            Intent intent = new Intent();
            intent.putExtra("className", HomePageActivity.class.getName());
            intent.putExtra("musicList", musicList);
            intent.putExtra("index", curIndex);
            
            skipManager.skipActivity(activity, MusicPlayActivity.class, intent, true);
            break;
        case R.id.bottom_music_nextsong_btn:
            //下一首
            startMusicService(curIndex, "nextSong");
            break;
        case R.id.bottom_music_menu_btn:
            //打开菜单
            activity.openOptionsMenu();
            break;
        default:
            break;
        }
    }
    
    private String flag = "free";
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
        case R.id.bottom_music_play_pause_btn:
            Log.i(TAG,"---播放按钮被点击了");
            if(isChecked){
                flag = "pause";
            }else{
                flag = "play";
            }
            
            startMusicService(curIndex, flag);
            break;
        default:
            break;
        }
    }
    private Intent intent;
    private void startMusicService(final int curIndex, final String flag){
        new Thread(new Runnable() {
            @Override
            public void run() {
                intent = new Intent();
                intent.putExtra("musicList", musicList);
                intent.putExtra("index", curIndex);
                intent.putExtra("flag", flag);
                intent.setAction("com.qing.ttdt.service.musicPlayService");
//        intent.setClass(activity, MusicPlayService.class);
                activity.getApplicationContext().startService(intent);
            }
        }).start();
    }
    
}
