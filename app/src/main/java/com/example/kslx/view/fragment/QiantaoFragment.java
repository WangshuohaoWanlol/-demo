package com.example.kslx.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.kslx.App;
import com.example.kslx.R;
import com.example.kslx.adapter.QiantaoAdapter;
import com.example.kslx.adapter.RecycleAdapter;
import com.example.kslx.callback.Myonlinckset;
import com.example.kslx.model.User;
import com.example.kslx.model.bean.MainBannerBean;
import com.example.kslx.model.bean.MainItemBean;
import com.example.kslx.persenter.Mpersentertwo;
import com.example.kslx.view.Mviewtwo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QiantaoFragment extends Fragment implements Mviewtwo {


    private Mpersentertwo mpersentertwo;
    private RecyclerView mRecy;
    private ArrayList<MainItemBean.DataBean.DatasBean> itemdata;
    private ArrayList<MainBannerBean.DataBean> beans;
    private RecycleAdapter adapter;
    private int cid;
    private int paeg;
    private SmartRefreshLayout sms;
    private int page;
    private String TAG = "QiantaoFragment";
    private int poinstion1;

    public QiantaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qiantao, container, false);
        //去P请求数据
        mpersentertwo = new Mpersentertwo(this);
        cid = getArguments().getInt("id");
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecy = view.findViewById(R.id.mRecy);
        sms = view.findViewById(R.id.sms);
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        //条目
        itemdata = new ArrayList<>();
        //轮播图

        beans = new ArrayList<>();
        adapter = new RecycleAdapter(itemdata, beans, getActivity());
        mRecy.setAdapter(adapter);
        sms.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                paeg++;
                initData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                itemdata.clear();
                page = 1;
                initData();
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setMyonlinckset(new Myonlinckset() {

            @Override
            public void setlonglick(int poinstion) {
                poinstion1 = poinstion;
                initPop();
            }
        });
    }

    private void initPop() {
        //长按弹出pop
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);

        final PopupWindow pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new ColorDrawable());
        pw.showAtLocation(view, Gravity.CENTER, 0, 0);

        final Button btn_ok = view.findViewById(R.id.btn_ok);
        final Button btn_delete = view.findViewById(R.id.btn_delete);
        final Button btn_update = view.findViewById(R.id.btn_update);


        final WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getActivity().getWindow().setAttributes(attributes);

        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                final WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });

        //删除
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemdata.remove(poinstion1);
                Toast.makeText(getActivity(), "这就给我删了？？？", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                pw.dismiss();
            }
        });

        final View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
        final String desc = itemdata.get(poinstion1).getDesc();
        final EditText ed_text = view1.findViewById(R.id.ed_text);
        ed_text.setText(desc);

        //修改
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("修改数据")
                        .setView(view1)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String string = ed_text.getText().toString();
                                MainItemBean.DataBean.DatasBean datasBean = itemdata.get(poinstion1);
                                datasBean.setTitle(string);
                                adapter.notifyDataSetChanged();

                            }
                        }).show();
                pw.dismiss();

            }
        });

        //收藏

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User();
                final MainItemBean.DataBean.DatasBean datasBean = itemdata.get(poinstion1);
                user.setMName(datasBean.getDesc());
                user.setMSrc(datasBean.getEnvelopePic());
                final long insert = App.getDaoSession().getUserDao().insert(user);
                if (insert > 0) {
                    Toast.makeText(getActivity(), "添加到数据库成功", Toast.LENGTH_SHORT).show();
                }
                pw.dismiss();
            }
        });
    }

    private void initData() {
        //条目
        paeg = 1;
        mpersentertwo.getData(paeg, cid);
        if (cid == 294) {
            mpersentertwo.getbannerdata();
        }
    }


    @Override
    public void setitemdatas(Object object) {
        if (object instanceof MainItemBean) {
            MainItemBean mainItem = (MainItemBean) object;
            List<MainItemBean.DataBean.DatasBean> datas = mainItem.getData().getDatas();
            itemdata.addAll(datas);
            sms.finishRefresh();
            sms.finishLoadMore();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setBenner(Object object) {
        if (object instanceof MainBannerBean) {
            MainBannerBean mainBannerBean = (MainBannerBean) object;
            List<MainBannerBean.DataBean> data = mainBannerBean.getData();
            beans.addAll(data);
            sms.finishRefresh();
            sms.finishLoadMore();
            adapter.notifyDataSetChanged();
        }
    }

}
