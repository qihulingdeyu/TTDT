package com.qing.ttdt.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.CustomHomePageAdapter;
import com.qing.ttdt.utils.HomePageUtils;

public class CustomHomePageActivity extends BaseActivity {

    private ArrayList<HashMap<String,Object>> data;
    private static final String PREFER_SETTING = "SettionInfo";
    private SharedPreferences prefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_homepage);

        prefer = getSharedPreferences(PREFER_SETTING, MODE_PRIVATE);
        data = HomePageUtils.getCustomHomePageData();

        CustomHomePageAdapter adapter = new CustomHomePageAdapter(mActivity, prefer, data);;
        ListView homepage_list = (ListView) findViewById(R.id.alter_homepage_item);
        //homepage_list.setDivider(currentActivity.getResources().getDrawable(android.R.drawable.divider_horizontal_textfield));
        //homepage_list.setDividerHeight(1);
        homepage_list.setAdapter(adapter);

        //点击后显示，测试用
        /*homepage_list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(CustomHomePageActivity.this, "点击了第"+position+"项", Toast.LENGTH_SHORT).show();
            }
        });*/


        ImageButton alter_hp2homepage = (ImageButton) findViewById(R.id.alter_hp2homepage);
        alter_hp2homepage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(HomePageActivity.class, null, true);
            }
        });
    }

}
