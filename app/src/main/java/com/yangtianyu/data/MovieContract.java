package com.yangtianyu.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

/**
 * Created by YANG on 2017/12/26.
 */

public class MovieContract {

    public static final String AUTHORITY = "com.yangtianyu.popularmovie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIE = "movie";

    public static class MovieEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "poster_path";
    }
}
