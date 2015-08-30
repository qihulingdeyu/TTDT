package com.qing.ttdt.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.FriendsListAdapter;

public class AttentionFriendsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attentionfriends);

        ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> map = null;
        for(int i=0;i<10;i++){
            map = new HashMap<String, Object>();
            map.put("friend_icon", R.drawable.icon_avatar_default);
            map.put("friend_name", "陈奕迅");
            map.put("friend_fans_count", "粉丝： 1234");
            map.put("friend_info", "香港男歌手");
            map.put("friend_user_v", R.drawable.user_v);
            data.add(map);
        }

        ListView friend_list = (ListView) findViewById(R.id.friend_list);
        friend_list.setAdapter(new FriendsListAdapter(AttentionFriendsActivity.this, data));

        ImageButton attentionfriends2homepage = (ImageButton) findViewById(R.id.attentionfriends2homepage);
        attentionfriends2homepage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(mActivity, HomePageActivity.class, null, true);
            }
        });

        Button user_login = (Button) findViewById(R.id.user_login);
        user_login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(mActivity, UserLoginActivity.class, null, true);
            }
        });
    }

}
