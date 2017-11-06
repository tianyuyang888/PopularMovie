package com.yangtianyu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class MovieDetailsEntity implements Parcelable {

    /**
     * adult : false
     * backdrop_path : /tcheoA2nPATCm2vvXw2hVQoaEFD.jpg
     * belongs_to_collection : null
     * budget : 35000000
     * genres : [{"id":12,"name":"冒险"},{"id":18,"name":"剧情"},{"id":27,"name":"恐怖"}]
     * homepage :
     * id : 346364
     * imdb_id : tt1396484
     * original_language : en
     * original_title : It
     * overview : 缅州因德瑞镇是一座由伐木业形成的城镇。但从建镇那天起，这里似乎就有一个小丑存在，但他绝非善类，反而以残害儿童为乐。  被坏孩子们欺负并称为窝囊废俱乐部的比利、麦克、史坦、贝芙、艾迪、本和瑞奇组成了幸运七人组。比利的弟弟乔治雨天玩耍时撞见小丑，失去了性命。他们从麦克的 家族相册中得知小丑的存在，而小丑也开始威胁孩子们的生命。七人组的孩子为了给乔治报仇，并保护镇上的孩子们，他们决心消灭小丑。经过一番激战，小丑带伤逃遁，三十年后，这个邪恶的魔鬼重返德瑞小镇。
     * popularity : 740.821925
     * poster_path : /8bsmRHyfySOx8FQ8Ai0phfbF1DU.jpg
     * production_companies : [{"name":"New Line Cinema","id":12},{"name":"Vertigo Entertainment","id":829},{"name":"Lin Pictures","id":2723},{"name":"RatPac-Dune Entertainment","id":41624},{"name":"KatzSmith Productions","id":87671}]
     * production_countries : [{"iso_3166_1":"US","name":"United States of America"}]
     * release_date : 2017-09-05
     * revenue : 555575232
     * runtime : 0
     * spoken_languages : [{"iso_639_1":"de","name":"Deutsch"},{"iso_639_1":"en","name":"English"}]
     * status : Released
     * tagline :
     * title : 小丑回魂
     * video : false
     * vote_average : 7.4
     * vote_count : 2762
     */

    public boolean adult;
    public String backdrop_path = "";
    public MovieCollectionEntity belongs_to_collection;
    public double budget;
    public String homepage = "";
    public int id;
    public String imdb_id = "";
    public String original_language = "";
    public String original_title = "";
    public String overview = "";
    public double popularity;
    public String poster_path = "";
    public String release_date = "";
    public double revenue;
    public int runtime;
    public String status = "";
    public String tagline = "";
    public String title = "";
    public boolean video;
    public double vote_average;
    public int vote_count;
    public List<GenresEntity> genres;
    public List<CompanyEntity> production_companies;
    public List<CountryEntity> production_countries;
    public List<LanguageEntity> spoken_languages;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeParcelable(this.belongs_to_collection, flags);
        dest.writeDouble(this.budget);
        dest.writeString(this.homepage);
        dest.writeInt(this.id);
        dest.writeString(this.imdb_id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeDouble(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeDouble(this.revenue);
        dest.writeInt(this.runtime);
        dest.writeString(this.status);
        dest.writeString(this.tagline);
        dest.writeString(this.title);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
        dest.writeList(this.genres);
        dest.writeList(this.production_companies);
        dest.writeList(this.production_countries);
        dest.writeList(this.spoken_languages);
    }

    public MovieDetailsEntity() {
    }

    protected MovieDetailsEntity(Parcel in) {
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.belongs_to_collection = in.readParcelable(MovieCollectionEntity.class.getClassLoader());
        this.budget = in.readDouble();
        this.homepage = in.readString();
        this.id = in.readInt();
        this.imdb_id = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.popularity = in.readDouble();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.revenue = in.readDouble();
        this.runtime = in.readInt();
        this.status = in.readString();
        this.tagline = in.readString();
        this.title = in.readString();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
        this.genres = new ArrayList<GenresEntity>();
        in.readList(this.genres, GenresEntity.class.getClassLoader());
        this.production_companies = new ArrayList<CompanyEntity>();
        in.readList(this.production_companies, CompanyEntity.class.getClassLoader());
        this.production_countries = new ArrayList<CountryEntity>();
        in.readList(this.production_countries, CountryEntity.class.getClassLoader());
        this.spoken_languages = new ArrayList<LanguageEntity>();
        in.readList(this.spoken_languages, LanguageEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieDetailsEntity> CREATOR = new Parcelable.Creator<MovieDetailsEntity>() {
        @Override
        public MovieDetailsEntity createFromParcel(Parcel source) {
            return new MovieDetailsEntity(source);
        }

        @Override
        public MovieDetailsEntity[] newArray(int size) {
            return new MovieDetailsEntity[size];
        }
    };
}
