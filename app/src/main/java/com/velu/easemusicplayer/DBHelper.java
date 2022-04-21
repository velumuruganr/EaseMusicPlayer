package com.velu.easemusicplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String SONG_TABLE = "USER_TABLE";
    public static final String SONG_NAME = "SONG_NAME";
    public static final String SONG_ALBUM = "SONG_ALBUM";
    public static final String SONG_LINK = "SONG_LINK";

    public DBHelper(@Nullable Context context) {
        super(context, "EaseMusicPlayer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStmt = "CREATE TABLE " + SONG_TABLE + " (" + SONG_NAME + " TEXT, " + SONG_ALBUM + " TEXT, " + SONG_LINK + " TEXT)";

        sqLiteDatabase.execSQL(createTableStmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
