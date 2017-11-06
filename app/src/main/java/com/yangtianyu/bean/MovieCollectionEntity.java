package com.yangtianyu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianyu on 2017/10/26.
 */
public class MovieCollectionEntity implements Parcelable {
//    {
//        "id": 87096,
//            "name": "阿凡达（系列）",
//            "poster_path": "/nslJVsO58Etqkk17oXMuVK4gNOF.jpg",
//            "backdrop_path": "/9s4BM48NweGFrIRE6haIul0YJ9f.jpg"
//    }

    public int id;
    public String name = "";
    public String poster_path = "";
    public String backdrop_path = "";

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
    }

    public MovieCollectionEntity() {
    }

    protected MovieCollectionEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Parcelable.Creator<MovieCollectionEntity> CREATOR = new Parcelable.Creator<MovieCollectionEntity>() {
        @Override
        public MovieCollectionEntity createFromParcel(Parcel source) {
            return new MovieCollectionEntity(source);
        }

        @Override
        public MovieCollectionEntity[] newArray(int size) {
            return new MovieCollectionEntity[size];
        }
    };
}
