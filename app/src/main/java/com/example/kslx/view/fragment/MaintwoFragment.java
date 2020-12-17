package com.example.kslx.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kslx.App;
import com.example.kslx.R;
import com.example.kslx.adapter.UserAdapter;
import com.example.kslx.model.User;
import com.example.kslx.model.bean.MainBannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaintwoFragment extends Fragment {

//1
    private RecyclerView mRe;
        private List<User> users;
        private UserAdapter userAdapter;

    public MaintwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maintwo, container, false);

        initView(view);
        initData();
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            users.clear();
            initData();
        }
    }

    private void initView(View view) {
        mRe = view.findViewById(R.id.mRe);
        //设置布局管理器
        mRe.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    //数据库
    private void initData() {
    //查询数据库
        users = App.getDaoSession().getUserDao().loadAll();
        //写适配器
        userAdapter = new UserAdapter(getActivity(), users);
        if(mRe!=null){
            mRe.setAdapter(userAdapter);
        }
    }

}
