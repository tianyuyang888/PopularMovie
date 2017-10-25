package com.yangtianyu.bean;

/**
 * Created by yangtianyu on 2017/10/25.
 */
public class MovieEntity {
//    "vote_count": 2682,
//            "id": 346364,
//            "video": false,
//            "vote_average": 7.4,
//            "title": "小丑回魂",
//            "popularity": 689.387288,
//            "poster_path": "/8bsmRHyfySOx8FQ8Ai0phfbF1DU.jpg",
//            "original_language": "en",
//            "original_title": "It",
//            "genre_ids": [
//            12,
//            18,
//            27
//            ],
//            "backdrop_path": "/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg",
//            "adult": false,
//            "overview": "缅州因德瑞镇是一座由伐木业形成的城镇。但从建镇那天起，这里似乎就有一个小丑存在，但他绝非善类，反而以残害儿童为乐。  被坏孩子们欺负并称为窝囊废俱乐部的比利、麦克、史坦、贝芙、艾迪、本和瑞奇组成了幸运七人组。比利的弟弟乔治雨天玩耍时撞见小丑，失去了性命。他们从麦克的 家族相册中得知小丑的存在，而小丑也开始威胁孩子们的生命。七人组的孩子为了给乔治报仇，并保护镇上的孩子们，他们决心消灭小丑。经过一番激战，小丑带伤逃遁，三十年后，这个邪恶的魔鬼重返德瑞小镇。",
//            "release_date": "2017-09-05"
    public int vote_count;
    public int id;
    public Boolean video;
    public Boolean adult;
    public String title = "";
    public float popularity;
    public String poster_path = "";
    public String original_language = "";
    public String original_title = "";
    public String backdrop_path = "";
    public String overview = "";
    public String release_date = "";
    public int[] genre_ids;
}
