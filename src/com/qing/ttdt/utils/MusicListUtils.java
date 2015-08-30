package com.qing.ttdt.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import com.qing.ttdt.domain.MusicInfo;

public class MusicListUtils {

    private final String TAG = "MusicListUtils";
    
    public ArrayList<ContentValues> getMusicList(Context context) {

        Log.i("MusicListUtils","重新获取了");
        
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        String[] projection = { Media._ID, Media.DISPLAY_NAME, Media.TITLE,
                Media.ARTIST, Media.ALBUM, Media.YEAR, Media.SIZE,
                Media.DURATION, Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        
//        ArrayList<MusicInfo> musicList = new ArrayList<MusicInfo>();
        ArrayList<ContentValues> cv = new ArrayList<ContentValues>();
        if (cursor != null) {
            ContentValues values = null;
//            MusicInfo info;
            while (cursor.moveToNext()) {
//                ContentValues
                values = getContentValues(context, cursor);
                cv.add(values);
                
//                MusicInfo
//                info = getList(cursor);
//                musicList.add(info);
            }
        }
        cursor.close();
        return cv;
    }

    private ContentValues values;
    private String id;
    private String title;
    private String albumId;
    private String year;
    private ContentValues getContentValues(Context context, Cursor cursor) {
        values = new ContentValues();
        id = cursor.getString(cursor.getColumnIndexOrThrow(Media._ID));
        values.put("_id", id);
        values.put("displayName", cursor.getString(cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME)));
        
        title = cursor.getString(cursor.getColumnIndexOrThrow(Media.TITLE));
        if(title.startsWith("?")) title = "未知";
        values.put("title", title);
        
        values.put("artist", cursor.getString(cursor.getColumnIndexOrThrow(Media.ARTIST)));
        values.put("album", cursor.getString(cursor.getColumnIndexOrThrow(Media.ALBUM)));
        
        albumId = getAlbumId(context, id);
        values.put("albumId", albumId);
        
        year = cursor.getString(cursor.getColumnIndexOrThrow(Media.YEAR));
        if(year==null) year = "";
        values.put("year", year);
        
        values.put("size", cursor.getInt(cursor.getColumnIndexOrThrow(Media.SIZE)));
        values.put("duration", cursor.getInt(cursor.getColumnIndexOrThrow(Media.DURATION)));
        values.put("path", cursor.getString(cursor.getColumnIndexOrThrow(Media.DATA)));
        values.put("favourite", 0);
        values.put("quality", 0);
        
        return values;
    }

    private String albumIdUri;
    private Uri uri;
    private Cursor cursor;
    private int albumId2;
    private String getAlbumId(Context context, String id) {
        albumIdUri = "content://media/external/audio/media/#";
        uri = Uri.parse(albumIdUri);
        cursor = context.getContentResolver().query(uri, new String[]{"album_id"}, "_id=?", new String[]{id}, null);
        albumId2 = 0;
        if(cursor!=null){
            while(cursor.moveToNext()){
                albumId2 = cursor.getInt(0);
            }
            cursor.close();
        }
        if(albumId2<0){
            return "";
        }
//        Log.i(TAG,"AlbumArt="+albumId+"="+getAlbumArt(context, albumId2+""));
        return albumId2+"";
    }

    private String albumArtUri;
    private String albumArt = null;
    private String getAlbumArt(Context context, String albumId){
        albumArtUri = "content://media/external/audio/albums/"+ albumId;
        uri = Uri.parse(albumArtUri);
        cursor = context.getContentResolver().query(uri, new String[]{"album_art"}, null, null, null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                albumArt = cursor.getString(0);
            }
            cursor.close();
        }
        return albumArt;
    }
    
    private MusicInfo getList(Cursor cursor) {
        int favourite;
        int quality;
        MusicInfo info = new MusicInfo();
        info.setId(cursor.getString(cursor.getColumnIndexOrThrow(Media._ID)));
        info.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME)));
        info.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Media.TITLE)));
        info.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(Media.ARTIST)));
        info.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(Media.ALBUM)));
        info.setYear(cursor.getString(cursor.getColumnIndexOrThrow(Media.YEAR)));
        info.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(Media.SIZE)));
        info.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(Media.DURATION)));
        info.setPath(cursor.getString(cursor.getColumnIndexOrThrow(Media.DATA)));
        try {
            favourite = cursor.getInt(cursor.getColumnIndexOrThrow("favourite"));
        } catch (IllegalArgumentException e) {
            favourite = 0;
        }
        info.setFavourite(favourite);
        
        try {
            quality = cursor.getInt(cursor.getColumnIndexOrThrow("quality"));
        } catch (IllegalArgumentException e) {
            quality = 0;
        }
        info.setQuality(quality);
        return info;
    }
}
