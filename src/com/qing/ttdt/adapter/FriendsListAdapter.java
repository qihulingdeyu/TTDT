package com.qing.ttdt.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.listener.AddAttentionListener;

public class FriendsListAdapter extends MyBaseAdapter {

    private ArrayList<HashMap<String,Object>> data;

    public FriendsListAdapter(Context context, ArrayList<HashMap<String,Object>> data){
        super(context, true);
        this.data = data;
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
    ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.attentionfriends_list_item, null);
            holder = new ViewHolder();
            holder.friend_icon = (ImageView) convertView.findViewById(R.id.friend_icon);
            holder.friend_name = (TextView) convertView.findViewById(R.id.friend_name);
            holder.friend_fans_count = (TextView) convertView.findViewById(R.id.friend_fans_count);
            holder.friend_info = (TextView) convertView.findViewById(R.id.friend_info);
            holder.friend_user_v = (ImageView) convertView.findViewById(R.id.friend_user_v);
            holder.add_attention = (Button) convertView.findViewById(R.id.add_attention);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.friend_icon.setImageResource((Integer) data.get(position).get("friend_icon"));
        holder.friend_name.setText((CharSequence) data.get(position).get("friend_name"));
        holder.friend_fans_count.setText((CharSequence) data.get(position).get("friend_fans_count"));
        holder.friend_info.setText((CharSequence) data.get(position).get("friend_info"));
        holder.friend_user_v.setImageResource((Integer) data.get(position).get("friend_user_v"));
        holder.add_attention.setOnClickListener(new AddAttentionListener(mContext));
        return convertView;
    }

    private class ViewHolder{
        ImageView friend_icon,friend_user_v;
        TextView friend_name,friend_fans_count,friend_info;
        Button add_attention;
    }
    
}
