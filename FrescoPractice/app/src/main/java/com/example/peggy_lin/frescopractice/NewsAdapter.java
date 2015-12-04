package com.example.peggy_lin.frescopractice;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Peggy_Lin on 2015/12/4.
 */
public class NewsAdapter extends
        RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public static final String TAG = "NewsAdapter";

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public SimpleDraweeView mDrawee;
        public TextView mTitle;
        public TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            mDrawee = (SimpleDraweeView) itemView.findViewById(R.id.news_drawee);
            mTitle = (TextView) itemView.findViewById(R.id.news_title);
            mDescription = (TextView) itemView.findViewById(R.id.news_description);
        }
    }
    private List<News> mNews;

    // Pass in the contact array into the constructor
    public NewsAdapter(List<News> news) {
        mNews = news;
    }


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        News news = mNews.get(position);

        // Set item views based on the data model
        SimpleDraweeView draweeView = viewHolder.mDrawee;
        TextView tvTitle = viewHolder.mTitle;
        TextView tvDescription = viewHolder.mDescription;

        Uri uri = Uri.parse(news.getURI());
        draweeView.setImageURI(uri);
        Log.d(TAG, "URI:" + news.getTitle());
        tvTitle.setText(news.getTitle());
        tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }
}
