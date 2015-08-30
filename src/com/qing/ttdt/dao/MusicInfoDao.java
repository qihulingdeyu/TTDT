package com.qing.ttdt.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qing.ttdt.db.MusicDB;
import com.qing.ttdt.domain.MusicInfo;

public class MusicInfoDao {

    private final String TAG = "MusicInfoDao";
    private MusicDB operHelper = null;
    private SQLiteDatabase db = null;
    private Cursor cursor = null;
    public static boolean flag = false;
    private static ArrayList<MusicInfo> musicList = null;
    private static ArrayList<MusicInfo> favouriteList = null;

    public MusicInfoDao(Context context) {
        operHelper = new MusicDB(context);
    }

    public long add(List<ContentValues> list) {
        db = operHelper.getWritableDatabase();
        long count = 0;
        for (ContentValues values : list) {
            db.insert("music_info", null, values);
            count++;
        }
        db.close();
        return count;

    }

    public int delete(String id) {
        db = operHelper.getWritableDatabase();
        int count = db.delete("music_info", "_id=?", new String[] { id });
        db.close();
        return count;
    }

    public int update(String id, ContentValues values) {
        db = operHelper.getWritableDatabase();
        int count = db.update("music_info", values, "_id=?", new String[] { id });
        db.close();
        return count;
    }

    public ArrayList<MusicInfo> getByType(int type, String selection, String[] selectionArgs){
        if(type==1){
            if(musicList==null){
                musicList = getAll(selection, selectionArgs);
            }
            return musicList;
        }else if(type==2){
            if(favouriteList==null || flag==true){
                favouriteList = getAll(selection, selectionArgs);
            }
            return favouriteList;
        }
        return null;
    }
    
    private ArrayList<MusicInfo> getAll(String selection, String[] selectionArgs) {
        db = operHelper.getReadableDatabase();
        cursor  = db.query("music_info", null, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            ArrayList<MusicInfo> list = new ArrayList<MusicInfo>();
            MusicInfo info = null;
            while (cursor.moveToNext()) {
                info = new MusicInfo();
                
                info.setId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
                info.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow("displayName")));
                info.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                info.setArtist(cursor.getString(cursor.getColumnIndexOrThrow("artist")));
                info.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow("album")));
                info.setAlbumId(cursor.getString(cursor.getColumnIndexOrThrow("albumId")));
                info.setYear(cursor.getString(cursor.getColumnIndexOrThrow("year")));
                
                info.setSize(cursor.getInt(cursor.getColumnIndexOrThrow("size")));
                info.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow("duration")));
                info.setPath(cursor.getString(cursor.getColumnIndexOrThrow("path")));
                info.setFavourite(cursor.getInt(cursor.getColumnIndexOrThrow("favourite")));
                info.setQuality(cursor.getInt(cursor.getColumnIndexOrThrow("quality")));
                
//                Log.i(TAG,info.toString());
                list.add(info);
            }
            cursor.close();
            db.close();
            return list;
        }
        return null;
    }

    public Cursor getById(String id) {
        db = operHelper.getReadableDatabase();
        Cursor cursor = db.query("music_info", null, "_id=?",
                new String[] { id }, null, null, null);
        db.close();
        return cursor;
    }
}
