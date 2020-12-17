package com.example.kslx.persenter;

import com.example.kslx.callback.RecycleCallBack2;
import com.example.kslx.callback.beannerdatacallback;
import com.example.kslx.model.Mmodeltwo;
import com.example.kslx.view.Mviewtwo;
import com.example.kslx.view.fragment.QiantaoFragment;

public class Mpersentertwo {
    private Mviewtwo mviewtwo;
    private final Mmodeltwo mmodeltwo;

    public Mpersentertwo(Mviewtwo mviewtwo) {


        this.mviewtwo = mviewtwo;
        mmodeltwo = new Mmodeltwo();

    }

    public void getData(int paeg, int cid) {
        mmodeltwo.setitemData(paeg,cid,new RecycleCallBack2() {
            @Override
            public void oncallback2(Object object) {
                mviewtwo.setitemdatas(object);
            }
        });
    }

    public void getbannerdata() {
        mmodeltwo.databeanner(new beannerdatacallback() {
            @Override
            public void beammersentercg(Object object) {
                mviewtwo.setBenner(object);
            }
        });
    }
}
