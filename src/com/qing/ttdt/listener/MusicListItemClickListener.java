package com.qing.ttdt.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.qing.ttdt.R;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.notify.PlayNotify;

public class MusicListItemClickListener implements OnItemClickListener {

    private final String TAG = "MusicListItemClickListener";
    private Context context;
    private ArrayList<MusicInfo> musicList;
    
    public MusicListItemClickListener(Context context, ArrayList<MusicInfo> musicList){
        this.context = context;
        this.musicList = musicList;
    }

    private View oldview = null;
    private Intent intent;
    private int index = 0;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView list_play_state = null;
        if(oldview!=null){
            list_play_state = (ImageView) oldview.findViewById(R.id.list_play_state);
            list_play_state.setVisibility(View.GONE);
        }
        oldview = view;
        list_play_state = (ImageView) view.findViewById(R.id.list_play_state);
        list_play_state.setVisibility(View.VISIBLE);
        
        index = position;
        //新建线程开启播放服务
        new Thread(new Runnable() {
            @Override
            public void run() {
                intent = new Intent();
                intent.putExtra("musicList", musicList);
                intent.putExtra("index", index);
                intent.putExtra("flag", "free");
                intent.setAction("com.qing.ttdt.service.musicPlayService");
                context.startService(intent);
                Log.i(TAG,context.toString());
            }
        }).start();
        
        //更新底部UI
        new BottomMusicPlayListener((Activity) context, musicList, position);
        
        //发送通知
        new PlayNotify(context,musicList.get(position));
    }
}
