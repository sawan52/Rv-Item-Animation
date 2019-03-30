package com.example.rvitemanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable {


    private Context mContext;
    private List<NewsItem> mData;
    private List<NewsItem> mDataFiltered;
    private boolean isDark = false;

    // added a new constructor for Dark Mode
    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
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

//        newsViewHolder.heading.setText(mData.get(position).getTitle());
//        newsViewHolder.description.setText(mData.get(position).getContent());
//        newsViewHolder.date.setText(mData.get(position).getDate());
//        newsViewHolder.userImage.setImageResource(mData.get(position).getUserPhoto());


        // change all the Views according to Filter...(mData changes to mDataFiltered)
        newsViewHolder.heading.setText(mDataFiltered.get(position).getTitle());
        newsViewHolder.description.setText(mDataFiltered.get(position).getContent());
        newsViewHolder.date.setText(mDataFiltered.get(position).getDate());
        newsViewHolder.userImage.setImageResource(mDataFiltered.get(position).getUserPhoto());

    }

    @Override
    public int getItemCount() {
//        return mData.size();
        return mDataFiltered.size();
    }

    // implements Filterable interface and override this method to perform search/filter related operation
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String KEY = constraint.toString(); // get the corresponding searching text from Search edit Text
                if (KEY.isEmpty()){

                    // if nothing is searched then set the mDataFiltered list to mData List
                    mDataFiltered = mData;
                }
                else {

                    // create a new List for filtered / searched data
                    List<NewsItem> listFiltered = new ArrayList<>();

                    // now search in each row of NewsItem type data from mData List...
                    for (NewsItem row : mData){

                        // if any Title row contains that KEY then add it in the listFiltered of type List
                        if (row.getTitle().toLowerCase().contains(KEY.toLowerCase())){
                            listFiltered.add(row);
                        }
                        // else if any Content row contains that KEY then add it in the listFiltered of type List
                        else if (row.getContent().toLowerCase().contains(KEY.toLowerCase())){
                            listFiltered.add(row);
                        }
                    }

                    // now set the listFiltered data in mDataFiltered List after filtering / searching the KEY from mData List items
                    mDataFiltered = listFiltered;
                }

                // now return the filterResult
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                // publish the filter result and notify the change on the View/Screen
                mDataFiltered = (List<NewsItem>) results.values;
                notifyDataSetChanged();

            }
        };


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
