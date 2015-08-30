package com.qing.ttdt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * 适配器基类
 */
public abstract class MyBaseAdapter extends BaseAdapter {

    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;

    public MyBaseAdapter(Context context){
        this(context, false);
    }

    public MyBaseAdapter(Context context, boolean inflater){
        mContext = context;
        if(inflater){
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }
}
