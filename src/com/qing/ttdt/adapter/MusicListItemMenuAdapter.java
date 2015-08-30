package com.qing.ttdt.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qing.ttdt.R;

public class MusicListItemMenuAdapter extends MyBaseAdapter {

    private ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
    String[] txt = {"删除","添加到...","发送","设为铃声","歌曲信息"};
    int[] icon = {R.drawable.icon_list_context_menu_remove,R.drawable.icon_list_context_menu_add_to,
            R.drawable.icon_list_context_menu_share,R.drawable.icon_list_context_menu_set_as_ringtone,
            R.drawable.icon_list_context_menu_media_info};
    HashMap<String,Object> map = null;
    
    public MusicListItemMenuAdapter(Context context) {
        super(context,true);
        for(int i=0;i<txt.length;i++){
            map = new HashMap<String,Object>();
            map.put("menu_item_icon", icon[i]);
            map.put("menu_item_txt", txt[i]);
            data.add(map);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.musiclist_item_menu_lv_item, null);
            holder = new ViewHolder();
            holder.menu_item_icon = (ImageView) convertView.findViewById(R.id.menu_item_icon);
            holder.menu_item_txt = (TextView) convertView.findViewById(R.id.menu_item_txt);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.menu_item_icon.setImageResource((Integer) data.get(position).get("menu_item_icon"));
        holder.menu_item_txt.setText((CharSequence) data.get(position).get("menu_item_txt"));
        
        return convertView;
    }

    private class ViewHolder{
        ImageView menu_item_icon;
        TextView menu_item_txt;
    }
}
