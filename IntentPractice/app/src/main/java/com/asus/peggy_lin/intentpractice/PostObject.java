package com.asus.peggy_lin.intentpractice;

/**
 * Created by Peggy_Lin on 2015/10/23.
 */
public class PostObject {
    private String mName;
    private String mPost;

    PostObject (String mName, String mPost){
        this.mName = mName;
        this.mPost = mPost;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPost() {
        return mPost;
    }

    public void setmPost(String mPost) {
        this.mPost = mPost;
    }
}
