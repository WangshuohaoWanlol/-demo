package com.example.kslx.model;

import com.example.kslx.ApiServess;
import com.example.kslx.Logintercettor;
import com.example.kslx.callback.RecycleCallBack;
import com.example.kslx.model.bean.MainTabBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mmodel {
    public void getDataitem(final RecycleCallBack callBack) {
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Logintercettor()).build();
        new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiServess.BASK_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiServess.class)
                .getTabData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainTabBean mainTabBean) {
                        callBack.onCallBackOk(mainTabBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
