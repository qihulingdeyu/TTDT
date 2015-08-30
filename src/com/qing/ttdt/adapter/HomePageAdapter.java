package com.qing.ttdt.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qing.ttdt.R;

public class HomePageAdapter extends MyBaseAdapter {

    //private SharedPreferences prefer;
    private ArrayList<HashMap<String,Object>> newData = new ArrayList<HashMap<String,Object>>();
    
    private String itemName;
    private boolean visible;
    public HomePageAdapter(Context context,SharedPreferences prefer, ArrayList<HashMap<String,Object>> data){
        super(context,true);
        //this.prefer = prefer;
        for (HashMap<String, Object> map : data) {
            itemName = map.get("homepage_gv_txt").toString();
            visible = Boolean.parseBoolean(map.get("visible").toString());
            if(prefer.getBoolean(itemName, visible)){
                newData.add(map);
            }
        }
    }
    @Override
    public int getCount() {
        return newData.size();
    }

    @Override
    public Object getItem(int position) {
        return newData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.homepage_gridview_item, null);
            holder = new ViewHolder();
            holder.homepage_gv_icon = (ImageView) convertView.findViewById(R.id.homepage_gv_icon);
            holder.homepage_gv_txt = (TextView) convertView.findViewById(R.id.homepage_gv_txt);
            holder.homepage_gv_num = (TextView) convertView.findViewById(R.id.homepage_gv_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.homepage_gv_icon.setImageResource(Integer.parseInt(newData.get(position).get("homepage_gv_icon").toString()));
        holder.homepage_gv_txt.setText(newData.get(position).get("homepage_gv_txt").toString());
        holder.homepage_gv_num.setText(newData.get(position).get("homepage_gv_num").toString());
        
        return convertView;
    }

    private class ViewHolder{
        ImageView homepage_gv_icon;
        TextView homepage_gv_txt,homepage_gv_num;
    }
}
