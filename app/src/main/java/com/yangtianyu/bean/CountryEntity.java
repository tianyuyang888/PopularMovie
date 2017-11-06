package com.yangtianyu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class CountryEntity implements Parcelable {

    /**
     * iso_3166_1 : US
     * name : United States of America
     */

    public String iso_3166_1;
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iso_3166_1);
        dest.writeString(this.name);
    }

    public CountryEntity() {
    }

    protected CountryEntity(Parcel in) {
        this.iso_3166_1 = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CountryEntity> CREATOR = new Parcelable.Creator<CountryEntity>() {
        @Override
        public CountryEntity createFromParcel(Parcel source) {
            return new CountryEntity(source);
        }

        @Override
        public CountryEntity[] newArray(int size) {
            return new CountryEntity[size];
        }
    };
}
