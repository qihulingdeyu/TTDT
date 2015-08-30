package com.qing.ttdt.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.qing.ttdt.R;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.service.MusicPlayService;
import com.qing.ttdt.utils.MusicInfoUtils;

public class MusicPlayActivity extends BaseActivity {

    private String preActivityName;
    private ArrayList<MusicInfo> musicList;
    private int curIndex;
    private MusicInfo musicInfo;
    
    private ImageButton backto_musiclist;
    private TextView songName;
    private TextView singerName;
    private TextView song_lyric;
    private SeekBar musicPlayProgressBar;
    private TextView song_playtime;
    private TextView song_duration;
    private ImageButton list_item_sort,previoussong,nextsong;
    private CheckBox play,music_favorite;
    
    private int curPosition;
    
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        
        Intent intent = getIntent();
        preActivityName = intent.getStringExtra("className");
        
        musicList = (ArrayList<MusicInfo>) intent.getSerializableExtra("musicList");
        curIndex = intent.getIntExtra("index", 0);
        
        musicInfo = musicList.get(curIndex);
        
        backto_musiclist = (ImageButton) findViewById(R.id.backto_musiclist);
        songName = (TextView) findViewById(R.id.songName);
        singerName = (TextView) findViewById(R.id.singerName);
        song_lyric = (TextView) findViewById(R.id.song_lyric);
        musicPlayProgressBar = (SeekBar) findViewById(R.id.musicPlayProgressBar);
        song_playtime = (TextView) findViewById(R.id.song_playtime);
        song_duration = (TextView) findViewById(R.id.song_duration);
        list_item_sort = (ImageButton) findViewById(R.id.list_item_sort);
        previoussong = (ImageButton) findViewById(R.id.previoussong);
        nextsong = (ImageButton) findViewById(R.id.nextsong);
        play = (CheckBox) findViewById(R.id.play);
        music_favorite = (CheckBox) findViewById(R.id.music_favorite);
        
        backto_musiclist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(preActivityName, null, true);
            }
        });
        
        songName.setText(musicInfo.getTitle());
        singerName.setText(musicInfo.getArtist());
        
        song_lyric.setText(musicInfo.getLrcPath());
        
        musicPlayProgressBar.setProgress(0);
        musicPlayProgressBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MusicPlayService.player.seekTo(curPosition);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                curPosition = progress;
                song_playtime.post(new Runnable() {
                    @Override
                    public void run() {
                        song_playtime.setText(MusicInfoUtils.formatDuration(curPosition));
                    }
                });
            }
        });
        
        
//        song_playtime;
        song_duration.setText(MusicInfoUtils.formatDuration(musicInfo.getDuration()));
        list_item_sort.setOnClickListener(new ImageButtonOnClick());
        previoussong.setOnClickListener(new ImageButtonOnClick());
        nextsong.setOnClickListener(new ImageButtonOnClick());
        play.setOnCheckedChangeListener(new CheckBoxOnChange());
        play.setChecked(true);
        music_favorite.setOnCheckedChangeListener(new CheckBoxOnChange());
        
        //监听歌曲是否变化
        changeListener();
    }
    class MusicPlayUpdateUITask extends AsyncTask<String[], Integer, String[]>{
        @Override
        protected String[] doInBackground(String[]... params) {
            return params[0];
        }
        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            songName.setText(result[0]);
            singerName.setText(result[1]);
        song_lyric.setText(result[2]);
            song_duration.setText(result[3]);
            play.setChecked(true);
        }
    }
    /*
     * 更新UI
     */
    private void UpdateUI(int index){
        musicInfo = musicList.get(index);
        String[] params = {musicInfo.getTitle(),musicInfo.getArtist(),musicInfo.getLrcPath(),MusicInfoUtils.formatDuration(musicInfo.getDuration())};
        new MusicPlayUpdateUITask().execute(params);
    }
    /*
     * 监听音乐是否变化
     */
    public void changeListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaPlayer player = MusicPlayService.player;
                while(true){
                    //更新进度条
                    if(player.isPlaying()){
                        //设置播放进度
                        musicPlayProgressBar.setProgress(player.getCurrentPosition());
                        try {//延迟更新
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    
                    //更新其它UI
                    if(MusicPlayService.isChange){
                        curIndex = MusicPlayService.getCurIndex();
                        UpdateUI(curIndex);
                        musicPlayProgressBar.setMax(player.getDuration());
                    }
                }
            }
        }).start();
    }
    
    private Intent intent;
    /*
     * 开启播放服务
     */
    private void startMusicService(final int index, final String flag){
        new Thread(new Runnable() {
            @Override
            public void run() {
                intent = new Intent();
                intent.putExtra("musicList", musicList);
                intent.putExtra("index", index);
                intent.putExtra("flag", flag);
                intent.setAction("com.qing.ttdt.service.musicPlayService");
                startService(intent);
            }
        }).start();
    }
    
    class ImageButtonOnClick implements OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.list_item_sort:
                Toast.makeText(MusicPlayActivity.this, "查看详细详细", Toast.LENGTH_SHORT).show();
                break;
            case R.id.previoussong:
                startMusicService(curIndex, "preSong");
                break;
            case R.id.nextsong:
                startMusicService(curIndex, "nextSong");
                break;
            default:
                break;
            }
        }
    }
    class CheckBoxOnChange implements OnCheckedChangeListener{
        private String flag = "free";
        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked) {
            switch (buttonView.getId()) {
            case R.id.play:
                if(isChecked){
                    flag = "pause";
                }else{
                    flag = "play";
                }
                startMusicService(curIndex,flag);
                break;
            case R.id.music_favorite:
                Toast.makeText(MusicPlayActivity.this, "喜爱", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
            }
        }
    }
    //退出对话框
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Builder builder = new AlertDialog.Builder(mActivity);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle(R.string.prompt);
            builder.setMessage(R.string.confirmQuit);
            builder.setPositiveButton(R.string.quit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showToast("点击了退出");
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
