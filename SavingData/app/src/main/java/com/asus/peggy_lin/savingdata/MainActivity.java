package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {
    TextView tv_def, tv_show;
    EditText et_user, et_blog;
    String filename = "userFile";
    String fileData = "";
    String userBlog = "";
    LinearLayout layout_new, layout_old, layout_write, layout_show;
    String sharedPreferenceName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //why use this?
        //Context context = this;

        //shared preferences when multi files
        //SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        //preference when only one activity uses
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        String defaultValue = "";
        sharedPreferenceName = sharedPref.getString(getString(R.string.saved_name_str), defaultValue);

        tv_def = (TextView) findViewById(R.id.save_data_text_view);

        layout_new = (LinearLayout) findViewById(R.id.layout_new_user);
        layout_old = (LinearLayout) findViewById(R.id.layout_old_user);
        layout_write = (LinearLayout) findViewById(R.id.layout_blog_write);
        layout_show = (LinearLayout) findViewById(R.id.layout_blog_show);

        if(tv_def.getVisibility() == View.VISIBLE && sharedPreferenceName != "") {
            tv_def.setText("Hello, " + sharedPreferenceName);
            et_blog = (EditText) findViewById(R.id.save_file_edit_text);
            layout_new.setVisibility(View.GONE);
            layout_old.setVisibility(View.VISIBLE);

            File file = new File(getFilesDir(), filename);
            if(file.exists()){
                layout_write.setVisibility(View.GONE);
                layout_show.setVisibility(View.VISIBLE);
                readFile();
            }
        }else if (sharedPreferenceName == "")
            tv_def.setText("Welcome, you're new!");
        else if(tv_def.getVisibility() != View.VISIBLE)
            recreate();
    }

    public void saveSharedPreference(View view){
        et_user = (EditText) findViewById(R.id.save_data_edit_text);
        String newNameStr = et_user.getText().toString();

//        Context context = this;
//        SharedPreferences sharedPref = getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_name_str), newNameStr);
        editor.commit();

        recreate();
    }

    public void deleteSharedPreference(View view){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().remove(getString(R.string.saved_name_str)).commit();

        delTextFile(view);

        recreate();
    }

    public void readFile(){
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

        tv_show = (TextView) findViewById(R.id.save_file_text_view);
        tv_show.setText(sb.toString());
    }

    public void saveTextFile(View view){
        FileOutputStream outputStream;
        userBlog = et_blog.getText().toString();

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(userBlog.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recreate();
    }

    public void delTextFile(View view){
        new File(getFilesDir(), filename).delete();

        recreate();
    }
}
