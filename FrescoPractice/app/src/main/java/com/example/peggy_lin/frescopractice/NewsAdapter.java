package com.example.peggy_lin.frescopractice;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;

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
        draweeView.setController(initDraweeController(draweeView, uri, news.getTitle()));

        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory() / 1024;
        long totalMemory = rt.totalMemory() / 1024;
        long freeMemory = rt.freeMemory() / 1024;
        long usedMemory = (totalMemory - freeMemory) / 1024;
        Log.d("onCreate", "maxMemory:" + Long.toString(maxMemory) + "KB");
        Log.d("onCreate", "totalMemory:" + Long.toString(totalMemory) + "KB");
        Log.d("onCreate", "freeMemory:" + Long.toString(freeMemory) + "KB");
        Log.d("onCreate", "usedMemory:" + Long.toString(usedMemory) + "KB");

//        draweeView.setImageURI(uri);
        Log.d(TAG, "URI:" + news.getTitle());
        tvTitle.setText(news.getTitle());
        tvDescription.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    private DraweeController initDraweeController(SimpleDraweeView draweeView, Uri uri, String title){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(initControllerListener(title))
                .setTapToRetryEnabled(true)
                .setOldController(draweeView.getController())
                .setUri(uri)
                .build();
        return controller;
    }

    private ControllerListener initControllerListener(final String title) {
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>(){
            @Override
            public void onRelease(String id){
                FLog.d(TAG, title + " image just released");
            }

            @Override
            public void onSubmit(String id, Object callerContext) {
                FLog.d(TAG, title + " image request submitted");
            }

            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                FLog.d(TAG, title + " Final image received! " +
                                "Size %d x %d" +
                        "Quality level %d, good enough: %s, full quality: %s",
                        imageInfo.getWidth(),
                        imageInfo.getHeight(),
                        qualityInfo.getQuality(),
                        qualityInfo.isOfGoodEnoughQuality(),
                        qualityInfo.isOfFullQuality());
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                FLog.d(TAG, title + " Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                FLog.e(getClass(), throwable, title + " Error loading %s", id);
            }
        };
        return  controllerListener;
    }
}
