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

public class MainActivity extends Activity {
    TextView tv_def;
    EditText et;
    Button btn_save, btn_delete;
    LinearLayout layout_new, layout_old;
    String sharedPreferenceName = null;

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
        et = (EditText) findViewById(R.id.save_data_edit_text);
        btn_save = (Button) findViewById(R.id.save_data_btn_save);
        btn_delete = (Button) findViewById(R.id.save_data_btn_delete);
        layout_new = (LinearLayout) findViewById(R.id.layout_new_user);
        layout_old = (LinearLayout) findViewById(R.id.layout_old_user);

        if(tv_def.getVisibility() == View.VISIBLE && sharedPreferenceName != "") {
            tv_def.setText("Hello, " + sharedPreferenceName);
            layout_new.setVisibility(View.GONE);
            layout_old.setVisibility(View.VISIBLE);
        }else if (sharedPreferenceName == "")
            tv_def.setText("Welcome, you're new!");
        else if(tv_def.getVisibility() != View.VISIBLE)
            recreate();
    }

    public void saveSharedPreference(View view){
        String newNameStr = et.getText().toString();

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

        recreate();
    }

    public void saveTextFile(View view){

    }

}
