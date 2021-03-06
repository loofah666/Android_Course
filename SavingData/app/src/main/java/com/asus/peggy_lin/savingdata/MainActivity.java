package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends ActionBarActivity {
    Toolbar toolbar;
    TextView tv_def, tv_show;
    EditText et_user, et_write;
    String filename = "userFile";
    String TAG_MAIN = "TAG_MAIN";
    String fileData, userBlog, sharedPreferenceName = "";
    LinearLayout layout_new, layout_old, layout_write, layout_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        //why use this?
        //Context context = this;

        //shared preferences when multi files
        //key static final, no need for string
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        //preference when only one activity uses
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        //if string not found then defaultValue
        String defaultValue = "";
        sharedPreferenceName = sharedPref.getString(getString(R.string.saved_name_str), defaultValue);

        //init views
        tv_def = (TextView) findViewById(R.id.save_data_text_view);
        layout_new = (LinearLayout) findViewById(R.id.layout_new_user);
        layout_old = (LinearLayout) findViewById(R.id.layout_old_user);
        layout_write = (LinearLayout) findViewById(R.id.layout_blog_write);
        layout_show = (LinearLayout) findViewById(R.id.layout_blog_show);


        if(sharedPreferenceName != "") {
            //not first time using
            oldUser(sharedPreferenceName);

            File file = new File(getFilesDir(), filename);
            Long result = file.getFreeSpace()/1048576;
            Log.d(TAG_MAIN, result.toString()+"MB");

            if(file.exists())
                //if intro text is saved before
                readFile();
            else
                //if intro text is not found
                editFile();
        }else
            //first time using
            newUser();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void saveSharedPreference(View view){
        et_user = (EditText) findViewById(R.id.save_data_edit_text);
        String newNameStr = et_user.getText().toString();

//        Context context = this;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        //write into sharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_name_str), newNameStr);
        editor.commit();

        //switch to oldUser mode and editFile as default
        oldUser(newNameStr);
        editFile();
    }

    public void deleteSharedPreference(View view){
        //delete intro text that goes with user
        delTextFile(view);
        //delete sharedPreferences
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        sharedPref.edit().remove(getString(R.string.saved_name_str)).commit();

        //switch to newUser mode
        newUser();
    }

    public void newUser(){
        layout_new.setVisibility(View.VISIBLE);
        layout_old.setVisibility(View.GONE);
        tv_def.setText("Welcome, you're new!");
    }
    public void oldUser(String sharedPreferenceName){
        layout_old.setVisibility(View.VISIBLE);
        layout_new.setVisibility(View.GONE);
        tv_def.setText("Hello, " + sharedPreferenceName);
    }

    public void readFile(){
        layout_write.setVisibility(View.GONE);
        layout_show.setVisibility(View.VISIBLE);

        //reading from file that's found
        StringBuilder sb = new StringBuilder();
        try{
            FileInputStream inputStream = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);

            while ((fileData = bufferedReader.readLine()) != null) {
                sb.append(fileData + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //show file content
        tv_show = (TextView) findViewById(R.id.save_file_text_view);
        tv_show.setText(sb.toString());

    }

    public void editFile(){
        layout_write.setVisibility(View.VISIBLE);
        layout_show.setVisibility(View.GONE);
    }

    public void saveTextFile(View view){
        et_write = (EditText) findViewById(R.id.save_file_edit_text);
        userBlog = et_write.getText().toString();

        //write into internal storage
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(userBlog.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //switch to readFile mode
        readFile();
    }

    public void delTextFile(View view){
        //delete intro text
        new File(getFilesDir(), filename).delete();
        //switch to editFile mode
        editFile();
    }

    public void goToShowExternal(View view){
        //go to External Storage Practice
        Intent intent = new Intent(this, ShowExternal.class);
        startActivity(intent);
    }
    public void goToFriends(View view){
        //go to External Storage Practice
        Intent intent = new Intent(this, ShowFriends.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


}
