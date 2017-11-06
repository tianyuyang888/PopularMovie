package com.yangtianyu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class CompanyEntity implements Parcelable {
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

    public CompanyEntity() {
    }

    protected CompanyEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CompanyEntity> CREATOR = new Parcelable.Creator<CompanyEntity>() {
        @Override
        public CompanyEntity createFromParcel(Parcel source) {
            return new CompanyEntity(source);
        }

        @Override
        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };
}
