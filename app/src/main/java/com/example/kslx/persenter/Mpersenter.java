package com.example.kslx.persenter;

import com.example.kslx.R;
import com.example.kslx.callback.RecycleCallBack;
import com.example.kslx.model.Mmodel;
import com.example.kslx.view.Mview;
import com.example.kslx.view.fragment.MainoneFragment;

public class Mpersenter {
    private Mview mview;
    private final Mmodel mmodel;

    public Mpersenter(Mview mview) {

        this.mview = mview;
        mmodel = new Mmodel();
    }

    public void getData() {
        mmodel.getDataitem(new RecycleCallBack() {
            @Override
            public void onCallBackOk(Object object) {
                mview.getData(object );
            }
        });
    }
}
