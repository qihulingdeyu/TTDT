package com.qing.ttdt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.HomePageAdapter;
import com.qing.ttdt.utils.HomePageUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends BaseActivity {

    private Class<?> clazz = null;
    
    private ArrayList<HashMap<String,Object>> data;
    private static final String PREFER_SETTING = "SettionInfo";
    private SharedPreferences prefer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage1);
        
        
        prefer = getSharedPreferences(PREFER_SETTING, MODE_PRIVATE);
        data = HomePageUtils.getCustomHomePageData();
        
        HomePageAdapter adapter = new HomePageAdapter(mActivity,prefer,data);
        GridView homepage_gridview = (GridView) findViewById(R.id.homepage_gridview_ly);
        homepage_gridview.setAdapter(adapter);
        
        homepage_gridview.setOnItemClickListener(new OnItemClickListener() {
            String txt;
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                //AppManager manager = AppManager.getAppManager();
                txt = ((TextView) view.findViewById(R.id.homepage_gv_txt)).getText().toString();
                for(HashMap<String,Object> map : data){
                    if(map.get("homepage_gv_txt").toString().equals(txt)){
                        clazz = (Class<?>) map.get("clazz");
                        break;
                    }
                }
                
                if(clazz!=null){
                    skipActivity(mActivity, clazz, null, true);
                }else{
                    showToast("未实现");
                }
            }
        });
        
        
        ImageButton bottom_singer_icon = (ImageButton) findViewById(R.id.bottom_singer_icon);
        bottom_singer_icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("className", this.getClass().getName());
                intent.putExtra("song_name", "正在播放。。。。。。正在播放。。。。。。");
                intent.putExtra("song_info", "陈奕迅");
                
                skipActivity(MusicPlayActivity.class, intent, true);
            }
        });
        
        //打开菜单
        ImageButton bottom_music_menu_btn = (ImageButton) findViewById(R.id.bottom_music_menu_btn);
        bottom_music_menu_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });
    
    }

    String str = null;
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
        case R.id.exit:
            str = "退出";
            break;
        case R.id.accountManager:
            str = "账号管理";
            //用户登录
            clazz = UserLoginActivity.class;
            break;
        case R.id.setting:
            str = "设置";
            break;
        case R.id.officialweibo:
            str = "官方微博";
            break;
        case R.id.nightMode:
            str = "夜间模式";
            break;
        case R.id.about:
            str = "关于";
            //关注好友
            clazz = AttentionFriendsActivity.class;
            break;
        case R.id.suggestionFeedback:
            str = "意见反馈";
            break;
        default:
            break;
        }
        if(clazz!=null){
            skipActivity(clazz, null, true);
        }else{
            showToast(str);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*
     * 双击退出
     */
    private long time = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            time = System.currentTimeMillis()-time;
            if(time>3000L){
                showToast("再点击一次");
                time = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
}
