package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class ShowExternal extends Activity {

    String LOG_EXT = "EXTERNAL_STORAGE";
    String LOG_FILES = "EXTERNAL_FILES";
    TextView tv;
    ListView lv;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_external);

        lv = (ListView) findViewById(R.id.external_list_view);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);

        if(isExternalStorageWritable()) {
            Log.d(LOG_EXT, "is Writable & Readable");
            getAlbumStorageDir();

        }
        else if(isExternalStorageReadable())
            Log.d(LOG_EXT, "is Readable");
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void getAlbumStorageDir() {
        //PRIVATE FILES: get external directory for this app only
        //File file = new File(getExternalFilesDir(null), "Text.txt");

        //PUBLIC FILES: get DCIM/Camera directory path
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera").toString();
        File file = new File(path);

        //if directory not exists
        if (!file.mkdirs()) {
            //create directory
            file.mkdir();
        }

        //list array of files in directory
        File file_list[] = file.listFiles();

        for (int i = 0; i < file_list.length; i++) {
            adapter.add(file_list[i].getName());
            Log.d(LOG_FILES, "FileName:" + file_list[i].getName());
        }
    }
}
