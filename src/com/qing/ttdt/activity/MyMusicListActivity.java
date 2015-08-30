package com.qing.ttdt.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.MusicListAdapter;
import com.qing.ttdt.dao.MusicInfoDao;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.listener.BottomMusicPlayListener;
import com.qing.ttdt.listener.MusicListItemClickListener;
import com.qing.ttdt.utils.HomePageUtils;

public class MyMusicListActivity extends BaseActivity {

    private ArrayList<MusicInfo> musicList;
    private ListView lv_musicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_musiclist);
        
        musicList = new MusicInfoDao(this).getByType(1,null,null);
        MusicListAdapter mla = new MusicListAdapter(MyMusicListActivity.this, musicList);
        
        lv_musicList = (ListView) findViewById(R.id.music_list);
        lv_musicList.setAdapter(mla);
        
        HomePageUtils.setListViewHeightBasedOnChildren(lv_musicList);
        //为每一个item绑定监听器
        lv_musicList.setOnItemClickListener(new MusicListItemClickListener(MyMusicListActivity.this, musicList));

        
        TextView music_count = (TextView) findViewById(R.id.music_count);
        music_count.setText(musicList.size()+"首歌曲");
        
        ImageButton mymusic2homepage = (ImageButton) findViewById(R.id.mymusic2homepage);
        mymusic2homepage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(HomePageActivity.class, null, true);
            }
        });
        
        //底部按钮监听
        new BottomMusicPlayListener(mActivity,musicList,0);
    }

}
