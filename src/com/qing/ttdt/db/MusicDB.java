package com.qing.ttdt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDB extends SQLiteOpenHelper {
    
    private static String name = "music.db";
    private static CursorFactory factory = null;
    private static int version = 1;
    
    
    public MusicDB(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table music_info(" +
                "_id integer primary key autoincrement," +
                "displayName varchar(80) not null," +
                "title varchar(50) not null," +
                "artist varchar(30) not null," +
                "album varchar(80) not null," +
                "albumId varchar(5) not null," +
                "year varchar(4) not null," +
                "size integer not null," +
                "duration integer not null," +
                "path varchar(255) not null," +
                "favourite integer not null," +
                "quality integer not null," +
                "lrcPath varchar(255)" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

}
