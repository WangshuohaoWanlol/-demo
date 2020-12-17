package com.example.kslx.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kslx.R;
import com.example.kslx.adapter.QiantaoAdapter;
import com.example.kslx.model.bean.MainTabBean;
import com.example.kslx.persenter.Mpersenter;
import com.example.kslx.view.Mview;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainoneFragment extends Fragment implements Mview {


    private TabLayout tab;
    private ViewPager mvp;
    private Mpersenter mpersenter;
    private List<MainTabBean.DataBean> data;
    private ArrayList<Fragment> fragments;

    public MainoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainone, container, false);
        mpersenter = new Mpersenter(this);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        tab = view.findViewById(R.id.tab);
        mvp = view.findViewById(R.id.mvp);
    }

    private void initData() {
        mpersenter.getData();
    }

    @Override
    public void getData(Object object) {
        if(object instanceof MainTabBean){
            MainTabBean tabBean = (MainTabBean) object;
            data = tabBean.getData();
            fragments = new ArrayList<>();
            for(int i=0;i<data.size();i++){
                QiantaoFragment qiantaoFragment = new QiantaoFragment();
                int id = data.get(i).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                qiantaoFragment.setArguments(bundle);
                fragments.add(qiantaoFragment);
            }
            QiantaoAdapter qiantaoAdapter = new QiantaoAdapter(getChildFragmentManager(),data,1,fragments);
            mvp.setAdapter(qiantaoAdapter);
            tab.setupWithViewPager(mvp);
        }
    }
}
