package com.example.kslx;


import com.example.kslx.model.bean.MainBannerBean;
import com.example.kslx.model.bean.MainItemBean;
import com.example.kslx.model.bean.MainTabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServess {
    //https://www.wanandroid.com/project/tree/json
    String BASK_URL = "https://www.wanandroid.com/";

    @GET("project/tree/json")
    Observable<MainTabBean> getTabData();
    //https://www.wanandroid.com/project/list/1/json?cid=294
    @GET("project/list/{page}/json?cid=294")
    Observable<MainItemBean>getItemData(@Path("page") int page, @Query("cid") int cid);
    @GET("banner/json")
    Observable<MainBannerBean>getBaeenerData();
}
