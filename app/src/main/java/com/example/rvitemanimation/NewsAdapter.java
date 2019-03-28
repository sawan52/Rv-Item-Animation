package com.example.rvitemanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    Context mContext;
    List<NewsItem> mData;
    boolean isDark = false;

    // added a new constructor for Dark Mode
    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
    }

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false);
        return new NewsViewHolder(layout);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {

        // bind data here

        // we apply animation to views here
        // first lets create an animation for user photo

        newsViewHolder.userImage.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));

        // lets create the animation for whole card
        // first lets create a reference to it

        newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));

        newsViewHolder.heading.setText(mData.get(position).getTitle());
        newsViewHolder.description.setText(mData.get(position).getContent());
        newsViewHolder.date.setText(mData.get(position).getDate());
        newsViewHolder.userImage.setImageResource(mData.get(position).getUserPhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView heading, description, date;
        ImageView userImage;
        RelativeLayout container;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            heading = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            date = itemView.findViewById(R.id.tv_date);
            userImage = itemView.findViewById(R.id.img_user);

            if (isDark) {

                setDarkTheme();
            }

        }


        private void setDarkTheme() {

            container.setBackgroundResource(R.drawable.card_bg_dark);
        }
    }

}
