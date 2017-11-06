package com.yangtianyu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class GenresEntity implements Parcelable {
    /**
     * id : 12
     * name : 冒险
     */

    public int id;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public GenresEntity() {
    }

    protected GenresEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<GenresEntity> CREATOR = new Parcelable.Creator<GenresEntity>() {
        @Override
        public GenresEntity createFromParcel(Parcel source) {
            return new GenresEntity(source);
        }

        @Override
        public GenresEntity[] newArray(int size) {
            return new GenresEntity[size];
        }
    };
}
