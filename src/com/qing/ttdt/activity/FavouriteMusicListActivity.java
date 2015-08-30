package com.qing.ttdt.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.MusicListAdapter;
import com.qing.ttdt.dao.MusicInfoDao;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.listener.MusicListItemClickListener;
import com.qing.ttdt.utils.HomePageUtils;

public class FavouriteMusicListActivity extends BaseActivity {
    
    private ArrayList<MusicInfo> musicList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_musiclist);
        
        musicList = new MusicInfoDao(this).getByType(2,"favourite=?",new String[]{"1"});
        //添加自定义listview的适配器
        MusicListAdapter mla = new MusicListAdapter(FavouriteMusicListActivity.this, musicList);
        ListView music_list = (ListView) findViewById(R.id.music_list);
        music_list.setAdapter(mla);
        
        HomePageUtils.setListViewHeightBasedOnChildren(music_list);
        //为每一个item绑定监听器
        music_list.setOnItemClickListener(new MusicListItemClickListener(FavouriteMusicListActivity.this, musicList));
        
        TextView music_count = (TextView) findViewById(R.id.music_count);
        music_count.setText(musicList.size()+"首歌曲");
        
        ImageButton myfavourite2homepage = (ImageButton) findViewById(R.id.myfavourite2homepage);
        myfavourite2homepage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(HomePageActivity.class, null, true);
            }
        });
        
        ImageButton bottom_singer_icon = (ImageButton) findViewById(R.id.bottom_singer_icon);
        bottom_singer_icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("className", FavouriteMusicListActivity.class.getName());
                intent.putExtra("song_name", "十年");
                intent.putExtra("song_info", "陈奕迅");
                
                skipActivity(MusicPlayActivity.class, intent, false);
            }
        });
    }

}
