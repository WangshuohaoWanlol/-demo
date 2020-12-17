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
import com.example.kslx.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.user_layout, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //给数据库设置数据
        User user = users.get(position);
        Holder holder1 = (Holder) holder;
        holder1.tv_title.setText(user.getMName());
        Glide.with(context).load(user.getMSrc()).into((holder1).iv_img);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static
    class Holder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_img;
        public TextView tv_title;

        public Holder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_img = (ImageView) rootView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        }
    }
}
