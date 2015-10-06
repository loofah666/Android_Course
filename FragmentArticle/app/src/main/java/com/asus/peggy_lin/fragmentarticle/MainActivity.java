package com.asus.peggy_lin.fragmentarticle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    static String tagMain = "FRAG_main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tagMain, "onCreate()............");

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tagMain, "onResume()............");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tagMain, "onStart()............");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tagMain, "onPause()............");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tagMain, "onStop()............");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tagMain, "onRestart()............");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tagMain, "onDestroy()............");
    }
}
