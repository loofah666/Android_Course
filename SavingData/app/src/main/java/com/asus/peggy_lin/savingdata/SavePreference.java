package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Peggy_Lin on 2015/10/7.
 */
public class SavePreference extends Activity {
String sharedPreferenceName;
TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_preference);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "";
        sharedPreferenceName = sharedPref.getString(getString(R.string.saved_name_str), defaultValue);

        tv_show = (TextView) findViewById(R.id.show_data_text_view);
        tv_show.setText("Welcome " + sharedPreferenceName);
    }
}
