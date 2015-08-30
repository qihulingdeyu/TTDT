package com.qing.ttdt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.listener.MusicListFavouriteListener;
import com.qing.ttdt.listener.MusicListMediaItemMenuListener;

public class MusicListAdapter extends MyBaseAdapter {

    private ArrayList<MusicInfo> listData;
    private LayoutInflater myInflater;
    
    public MusicListAdapter(Context context, ArrayList<MusicInfo> listData){
        super(context);
        this.listData = listData;
        //myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myInflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    MusicInfo music;
    ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.musiclist_item, null);
            holder = new ViewHolder();
            holder.musiclist_favourite = (CheckBox) convertView.findViewById(R.id.musiclist_favourite);
            holder.musiclist_displayname = (TextView) convertView.findViewById(R.id.musiclist_displayname);
            holder.musiclist_artist = (TextView) convertView.findViewById(R.id.musiclist_artist);
            holder.media_item_menu = (Button) convertView.findViewById(R.id.media_item_menu);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        music = listData.get(position);
        holder.musiclist_favourite.setOnCheckedChangeListener(new MusicListFavouriteListener(mContext,music.getId()));
        holder.musiclist_favourite.setChecked(music.getFavourite()==1?true:false);
        holder.musiclist_displayname.setText(position+1+". "+music.getTitle());
        holder.musiclist_artist.setText(music.getArtist());
        
        holder.media_item_menu.setOnClickListener(new MusicListMediaItemMenuListener(mContext, music));
        return convertView;
    }
    
    private class ViewHolder{
        CheckBox musiclist_favourite;
        TextView musiclist_displayname,musiclist_artist;
        Button media_item_menu;
    }
    
//    @SuppressWarnings("unused")
//    private String getTime(int duration){
//        duration = duration/1000;
//        int minute = duration/60;
//        int hour = minute/60;
//        int second = duration%60;
//        minute = minute%60;
//        return String.format("%02d:%02d", minute,second);
//    }
}
