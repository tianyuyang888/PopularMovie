package com.yangtianyu.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.data.MovieContract.MovieEntry;

/**
 * Created by YANG on 2017/12/26.
 */

public class MovieDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " ("
                + MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MovieEntry.COLUMN_ID + " INTEGER NOT NULL,"
                + MovieEntry.COLUMN_VOTE_AVERAGE + " DOUBLE NOT NULL,"
                + MovieEntry.COLUMN_TITLE + " TEXT NOT NULL,"
                + MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL,"
                + MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL,"
                + MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL,"
                + " UNIQUE (" + MovieEntry.COLUMN_ID + ") ON CONFLICT REPLACE);";
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
