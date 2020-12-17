package com.example.kslx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kslx.R;
import com.example.kslx.callback.Myonlinckset;
import com.example.kslx.model.bean.MainBannerBean;
import com.example.kslx.model.bean.MainItemBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter {
    private ArrayList<MainItemBean.DataBean.DatasBean> itemdata;
    private ArrayList<MainBannerBean.DataBean> beans;
    private Context context;
    private ImageView r_iv;
    private TextView r_tv;
    private Myonlinckset myonlinckset;

    public void setMyonlinckset(Myonlinckset myonlinckset) {
        this.myonlinckset = myonlinckset;
    }

    public RecycleAdapter(ArrayList<MainItemBean.DataBean.DatasBean> itemdata, ArrayList<MainBannerBean.DataBean> beans, Context context) {
        this.itemdata = itemdata;
        this.beans = beans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = View.inflate(context, R.layout.banner_layout, null);
            return new Holder1(view);
        } else {
            View view = View.inflate(context, R.layout.itemdata_layout, null);
            return new Holder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if(type==0){

            Holder1 holder1 = (Holder1) holder;
           holder1.mBanner.setImages(beans).setImageLoader(new ImageLoader() {
               @Override
               public void displayImage(Context context, Object path, ImageView imageView) {
                   MainBannerBean.DataBean bean = (MainBannerBean.DataBean) path;
                   Glide.with(context).load(bean.getImagePath()).into(imageView);
               }
           }).start();
        }else{
            Holder2 holder2 = (Holder2) holder;
            holder2.r_tv.setText(itemdata.get(position).getTitle());
            Glide.with(context).load(itemdata.get(position).getEnvelopePic()).into(holder2.r_iv);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myonlinckset.setlonglick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size()>0?itemdata.size()-1:itemdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (beans.size() > 0 && position == 0) {
            return 0;
        } else {

        }  return 1;
        }

    public static
    class Holder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner mBanner;

        public Holder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mBanner = (Banner) rootView.findViewById(R.id.mBanner);
        }

    }

    public static
    class Holder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView r_iv;
        public TextView r_tv;

        public Holder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.r_iv = (ImageView) rootView.findViewById(R.id.r_iv);
            this.r_tv = (TextView) rootView.findViewById(R.id.r_tv);
        }

    }
}
