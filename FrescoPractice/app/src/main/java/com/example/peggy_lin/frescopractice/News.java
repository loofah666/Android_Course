package com.example.peggy_lin.frescopractice;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peggy_Lin on 2015/12/4.
 */
public class News {
    private String mTitle;
    private String mDescription;
    private String mURI;

    public News(String title, String description, String uri) {
        mTitle = title;
        mDescription = description;
        mURI = uri;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getURI() {
        return mURI;
    }
}
