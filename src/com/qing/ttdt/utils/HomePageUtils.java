package com.qing.ttdt.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.qing.ttdt.R;
import com.qing.ttdt.activity.CustomHomePageActivity;
import com.qing.ttdt.activity.FavouriteMusicListActivity;
import com.qing.ttdt.activity.MyMusicListActivity;

public class HomePageUtils {

    private Context context;
    public HomePageUtils(Context context){
        this.context = context;
    }
    
    //解决listview只显示一个item项的问题
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        if(listAdapter==null) { return; }
        int count = listAdapter.getCount();
        int totalHeight = 0;
        for(int i=0; i<count; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // measure the child view
            //listItem的根布局必须是LinearLayout，否则listItem.measure(0, 0);会报空指针
            listItem.measure(0, 0);
            // calculate the total height of items 
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams(); 
        // get divider height for all items and add the total height 
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        listView.setLayoutParams(params);
    }
    
    public static ArrayList<HashMap<String,Object>> data;
    
    static{
        data = new ArrayList<HashMap<String,Object>>();
        //主页的数据
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("clazz", MyMusicListActivity.class);
        map.put("visible", true);
        map.put("homepage_gv_icon", R.drawable.icon_local_music);
        map.put("homepage_gv_txt", "我的音乐");
        map.put("homepage_gv_num", 2);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", FavouriteMusicListActivity.class);
        map.put("visible", true);
        map.put("homepage_gv_icon", R.drawable.icon_favorites);
        map.put("homepage_gv_txt", "我的最爱");
        map.put("homepage_gv_num", 0);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", true);
        map.put("homepage_gv_icon", R.drawable.icon_library_music_circle);
        map.put("homepage_gv_txt", "音乐圈");
        map.put("homepage_gv_num", "");
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_folder_plus);
        map.put("homepage_gv_txt", "文件夹");
        map.put("homepage_gv_num", 2);
        map.put("item_icon", R.drawable.icon_subscription_folder);
        map.put("item_text", R.string.folder);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_artist_plus);
        map.put("homepage_gv_txt", "歌手");
        map.put("homepage_gv_num", 1);
        map.put("item_icon", R.drawable.icon_subscription_artist);
        map.put("item_text", R.string.singer);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_album_plus);
        map.put("homepage_gv_txt", "专辑");
        map.put("homepage_gv_num", 1);
        map.put("item_icon", R.drawable.icon_subscription_album);
        map.put("item_text", R.string.special);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_genre_plus);
        map.put("homepage_gv_txt", "风格");
        map.put("homepage_gv_num", 1);
        map.put("item_icon", R.drawable.icon_subscription_genre);
        map.put("item_text", R.string.style);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_recent_add_plus);
        map.put("homepage_gv_txt", "最近添加");
        map.put("homepage_gv_num", "");
        map.put("item_icon", R.drawable.icon_subscription_recent_add);
        map.put("item_text", R.string.recentAdd);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_recent_play_plus);
        map.put("homepage_gv_txt", "最近播放");
        map.put("homepage_gv_num", "");
        map.put("item_icon", R.drawable.icon_subscription_play);
        map.put("item_text", R.string.recentPlay);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", false);
        map.put("homepage_gv_icon", R.drawable.icon_new_playlist);
        map.put("homepage_gv_txt", "新建列表");
        map.put("homepage_gv_num", "");
        map.put("item_icon", R.drawable.icon_subscription_new_playlist);
        map.put("item_text", R.string.createNewList);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", null);
        map.put("visible", true);
        map.put("homepage_gv_icon", R.drawable.icon_download);
        map.put("homepage_gv_txt", "下载管理");
        map.put("homepage_gv_num", 2);
        data.add(map);
        
        map = new HashMap<String, Object>();
        map.put("clazz", CustomHomePageActivity.class);
        map.put("visible", true);
        map.put("homepage_gv_icon", R.drawable.icon_home_custom);
        map.put("homepage_gv_txt", "定制首页");
        map.put("homepage_gv_num", "");
        data.add(map);
        
        //定制首页数据
        /*
        int[] homepageitem_icon = {R.drawable.icon_subscription_folder,R.drawable.icon_subscription_artist,
                R.drawable.icon_subscription_album,R.drawable.icon_subscription_genre,R.drawable.icon_subscription_recent_add,
                R.drawable.icon_subscription_play,R.drawable.icon_subscription_new_playlist};
        
        int[] homepageitem_text = {R.string.folder,R.string.singer,R.string.special,
                R.string.style,R.string.recentAdd,R.string.recentPlay,R.string.createNewList};
        
        int count = homepageitem_icon.length;
        for(int i=0; i<count; i++){
            for(int j=i+3; j<count+3; j++){
                HashMap<String, Object> hm = data.get(j);
                hm.put("item_icon", homepageitem_icon[i]);
                hm.put("item_text", homepageitem_text[i]);
            }
        }*/
        
    }
    
    public static ArrayList<HashMap<String, Object>> getCustomHomePageData(){
        return data;
    }
    
    /*
     * 从arrays.xml文件中获取数据
     */
    @SuppressLint("Recycle")
    public void getResourceFromArrays(){
        Resources res = context.getResources();
        TypedArray icon = res.obtainTypedArray(R.array.homepageitem_icon);
        String[] txt = res.getStringArray(R.array.homepageitem_text);
        String[] chk = res.getStringArray(R.array.homepageitem_chbox);
        for (int i = 0; i < txt.length; i++) {
            System.out.println(icon.getResourceId(i, 0)+"*****"+txt[i]+"*****"+chk[i]);
        }
    }
    
    public static String getUTFString(String content){
        try {
            return new String(content.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
