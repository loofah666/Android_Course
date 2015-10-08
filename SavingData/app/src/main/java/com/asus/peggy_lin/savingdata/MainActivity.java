package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    TextView tv_def, tv_show;
    EditText et_user, et_write;
    String filename = "userFile";
    String fileData, userBlog, sharedPreferenceName = "";
    LinearLayout layout_new, layout_old, layout_write, layout_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //why use this?
        //Context context = this;

        //shared preferences when multi files
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
            if(file.exists())
                //if intro text is saved before
                readFile();
            else if(!file.exists())
                //if intro text is not found
                editFile();
        }else
            //first time using
            newUser();
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



}
