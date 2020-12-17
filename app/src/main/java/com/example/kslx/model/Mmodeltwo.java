package com.example.kslx.model;

import com.example.kslx.ApiServess;
import com.example.kslx.callback.RecycleCallBack2;
import com.example.kslx.callback.beannerdatacallback;
import com.example.kslx.model.bean.MainBannerBean;
import com.example.kslx.model.bean.MainItemBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mmodeltwo {
    public void setitemData(int paeg, int cid, final RecycleCallBack2 callBack2) {
        new Retrofit.Builder()
                .baseUrl(ApiServess.BASK_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServess.class)
                .getItemData(paeg, cid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainItemBean mainItemBean) {
                        callBack2.oncallback2(mainItemBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void databeanner(final beannerdatacallback bean) {
        new Retrofit.Builder()
                .baseUrl(ApiServess.BASK_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServess.class)
                .getBaeenerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBannerBean mainBannerBean) {
                        bean.beammersentercg(mainBannerBean);
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
