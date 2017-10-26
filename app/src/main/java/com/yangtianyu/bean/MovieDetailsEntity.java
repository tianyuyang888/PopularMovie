package com.yangtianyu.bean;

import java.util.List;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class MovieDetailsEntity {

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


}
