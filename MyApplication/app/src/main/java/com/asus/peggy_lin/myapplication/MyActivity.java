package com.asus.peggy_lin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MyActivity  extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = " com.asus.peggy_lin.myapplication.MESSAGE";
    public int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //在onCreate取得savedInstanceState
//        if(savedInstanceState!=null)
//            count = savedInstanceState.getInt("COUNT");

        //每次翻轉螢幕就累加一次count
        TextView textView = (TextView) findViewById(R.id.text_message);
        textView.setText("翻轉第"+count+"次");

    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void showMessage(View view){
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.text_message);
        textView.setText(count +": "+ message);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("COUNT", count + 1);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("COUNT");
    }

    @Override
    protected void onStart() {
        // The activity is about to become visible.
        CharSequence text = "onStart()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();
        super.onStart();
    }
    @Override
    protected void onResume() {
        CharSequence text = "onResume()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();

        //顯示翻轉次數
        TextView textView = (TextView) findViewById(R.id.text_message);
        textView.setText("翻轉第" + count + "次");

        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        // Another activity is taking focus (this activity is about to be "paused").
        CharSequence text = "onPause()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        // The activity is no longer visible (it is now "stopped")
        CharSequence text = "onStop()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed.
        CharSequence text = "onDestroy()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();
        super.onDestroy();
    }
    @Override
    protected void onRestart() {
        CharSequence text = "onRestart()";
        int time = Toast.LENGTH_SHORT;
        Toast.makeText(getApplicationContext(), text, time).show();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
