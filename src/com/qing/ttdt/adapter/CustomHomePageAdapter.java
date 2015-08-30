package com.qing.ttdt.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.qing.ttdt.R;
import com.qing.ttdt.listener.CustomHomePageCheckListener;

public class CustomHomePageAdapter extends MyBaseAdapter {

    private SharedPreferences prefer;
    private ArrayList<HashMap<String,Object>> newData;

    public CustomHomePageAdapter(Context context, SharedPreferences prefer, ArrayList<HashMap<String,Object>> data){
        super(context, true);
        this.prefer = prefer;
        this.newData = data;
    }
    
    @Override
    public int getCount() {
        return newData.size()-5;
    }

    @Override
    public Object getItem(int position) {
        return newData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    String itemName;
    boolean isck;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.custom_homepage_item, null);
            holder = new ViewHolder();
            holder.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
            holder.item_text = (TextView) convertView.findViewById(R.id.item_text);
            holder.item_chbox = (CheckBox) convertView.findViewById(R.id.item_chbox);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        position = position+3;
        
        holder.item_icon.setImageResource(Integer.parseInt(newData.get(position).get("item_icon").toString()));
        holder.item_text.setText(Integer.parseInt(newData.get(position).get("item_text").toString()));
        
        itemName = newData.get(position).get("homepage_gv_txt").toString();
        //必须先添加监听然后再进行赋值，否则会出错
        holder.item_chbox.setOnCheckedChangeListener(new CustomHomePageCheckListener(mContext,prefer,itemName));
        
        //isck = Boolean.valueOf(newData.get(position).get("visible").toString());
        //默认不选择
        isck = prefer.getBoolean(itemName, false);
        
        //取消默认的第一项
        if(!isck && position==3){
            holder.item_chbox.setChecked(false);
        }
        
        if(isck){
            holder.item_chbox.setChecked(true);
        }
        
        
        return convertView;
    }

    private class ViewHolder{
        ImageView item_icon;
        TextView item_text;
        CheckBox item_chbox;
    }
}
