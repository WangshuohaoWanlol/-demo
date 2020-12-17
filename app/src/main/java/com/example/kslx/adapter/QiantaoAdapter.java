package com.example.kslx.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kslx.model.bean.MainTabBean;

import java.util.ArrayList;
import java.util.List;

public class QiantaoAdapter extends FragmentPagerAdapter {
    private List<MainTabBean.DataBean> data;
    private ArrayList<Fragment> fragments;
    public QiantaoAdapter(FragmentManager fm, List<MainTabBean.DataBean> data, int behavior, ArrayList<Fragment> fragments) {
        super(fm, behavior);
        this.data = data;
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return data.get(position).getName();
    }
}
