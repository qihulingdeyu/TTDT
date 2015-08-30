package com.qing.ttdt.listener;

import com.qing.ttdt.dao.MusicInfoDao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MusicListFavouriteListener implements OnCheckedChangeListener {
    
    private Context context;
    private String musicId;
    private ContentValues values;
    public MusicListFavouriteListener(Context context, String musicId){
        this.context = context;
        this.musicId = musicId;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        
        values = new ContentValues();
        values.put("favourite", (isChecked==true ? "1" : "0"));
        new MusicInfoDao(context).update(musicId, values);
        MusicInfoDao.flag = true;
        Log.i("CheckedChanged",musicId+"  "+isChecked);
    }

}
