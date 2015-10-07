package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
TextView tv_def;
String sharedPreferenceName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "";
        sharedPreferenceName = sharedPref.getString(getString(R.string.saved_name_str), defaultValue);

        tv_def = (TextView) findViewById(R.id.save_data_text_view);
        if(tv_def.getVisibility() == View.VISIBLE && sharedPreferenceName != null)
            tv_def.setText("Hello, " + sharedPreferenceName);

    }

    public void saveSharedPreference(View view){
        EditText et = (EditText) findViewById(R.id.save_data_edit_text);
        String newNameStr = et.getText().toString();

        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_name_str), newNameStr);
        editor.commit();

        Intent intent = new Intent(this, SavePreference.class);
        startActivity(intent);
    }

}
