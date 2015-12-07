package com.example.peggy_lin.frescopractice;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScrollingActivity extends AppCompatActivity {

    public static final String TAG = "ScrollingActivity";
    private static final String mMainDiskCacheName = "newsImage";
    private static int mLastNewsId = 0;
    private int mMemoryCacheSize = 32 * 1024 * 1024; // 32MB
    private int mDiskCacheSize = 256 * 1024 * 1024; // 256MB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initFresco();
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRecyclerView();
    }

    private void initFresco(){
        Log.d(TAG, "cache dir:" + this.getCacheDir());

        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());

        //set max disk cache
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
                .setBaseDirectoryPath(this.getCacheDir())
                .setBaseDirectoryName(mMainDiskCacheName)
                .setMaxCacheSize(mDiskCacheSize)
                .build();

        //set config in ImagePipeline
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
                .setMainDiskCacheConfig(diskCacheConfig)
                .setRequestListeners(requestListeners)
                .setImageCacheStatsTracker(setImageTracker())
                .build();

        //Fresco initialization with config
        Fresco.initialize(getApplicationContext(), config);
    }

    private void initRecyclerView(){
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvNewsList);
        NewsAdapter adapter = new NewsAdapter(createNewsList(260));
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<News> createNewsList(int numNews) {
        List<News> news = new ArrayList<News>();
        Resources res = getResources();
        String[] newsUri = res.getStringArray(R.array.news_uri2);
        for (int i = 1; i <= numNews; i++) {
            news.add(new News("News " + ++mLastNewsId, " Description " + mLastNewsId, newsUri[i]));
        }

        return news;
    }

    private ImageCacheStatsTracker setImageTracker(){
        //set imageCacheStatsTracker
        ImageCacheStatsTracker imageCacheStatsTracker = new ImageCacheStatsTracker() {
            @Override
            public void onBitmapCachePut() {
                Log.d(TAG, "onBitmapCachePut");
            }

            @Override
            public void onBitmapCacheHit() {
                Log.d(TAG, "onBitmapCacheHit");
            }

            @Override
            public void onBitmapCacheMiss() {
                Log.d(TAG, "onBitmapCacheMiss");
            }

            @Override
            public void onMemoryCachePut() {
                Log.d(TAG, "onMemoryCachePut");
            }

            @Override
            public void onMemoryCacheHit() {
                Log.d(TAG, "onMemoryCacheHit");
            }

            @Override
            public void onMemoryCacheMiss() {
                Log.d(TAG, "onMemoryCacheMiss");
            }

            @Override
            public void onStagingAreaHit() {
                Log.d(TAG, "onStagingAreaHit");
            }

            @Override
            public void onStagingAreaMiss() {
                Log.d(TAG, "onStagingAreaMiss");
            }

            @Override
            public void onDiskCacheHit() {
                Log.d(TAG, "onDiskCacheHit");
            }

            @Override
            public void onDiskCacheMiss() {
                Log.d(TAG, "onDiskCacheMiss");
            }

            @Override
            public void onDiskCacheGetFail() {
                Log.d(TAG, "onDiskCacheGetFail");
            }

            @Override
            public void registerBitmapMemoryCache(CountingMemoryCache<?, ?> bitmapMemoryCache) {

            }

            @Override
            public void registerEncodedMemoryCache(CountingMemoryCache<?, ?> encodedMemoryCache) {

            }
        };
        return imageCacheStatsTracker;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
