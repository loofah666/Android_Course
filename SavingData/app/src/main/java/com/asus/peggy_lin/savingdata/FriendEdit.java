package com.asus.peggy_lin.savingdata;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class FriendEdit extends AppCompatActivity {

    private FriendDBHelper mDbHelper;
    private SQLiteDatabase mDBRead, mDBWrite;
    EditText ed_name, ed_phone, ed_fav;
    RadioButton rb_boy, rb_girl;
    private static final String TAG_EDIT = "FRIEND_EDIT";
    String mName, mPhone, mFav = "";
    Integer mGender, mId = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_edit);

        mDbHelper = new FriendDBHelper(this);

        initToolbar();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mId = extras.getInt("ID");
            mName = extras.getString("Name");
            mGender = extras.getInt("Gender");
            mPhone = extras.getString("Phone");
            mFav = extras.getString("Fav");
        }

        ed_name = (EditText) findViewById(R.id.edit_et_name);
        ed_phone = (EditText) findViewById(R.id.edit_et_phone);
        ed_fav = (EditText) findViewById(R.id.edit_et_fav);

        rb_boy = (RadioButton) findViewById(R.id.edit_rb_boy);
        rb_girl = (RadioButton) findViewById(R.id.edit_rb_girl);

        loadFriends();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_edit_show);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void loadFriends(){
        ed_name.setText(mName);
        ed_phone.setText(mPhone);
        ed_fav.setText(mFav);

        if(mGender>0)
            rb_girl.setChecked(true);
        else
            rb_boy.setChecked(true);
    }

    public void updateFriend(View view){
        String name = ed_name.getText().toString();
        String phone = ed_phone.getText().toString();
        String fav = ed_fav.getText().toString();
        Integer gender = 0;
        if(rb_boy.isChecked())
            gender = 0;
        else if(rb_girl.isChecked())
            gender = 1;


        mDBRead = mDbHelper.getReadableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FriendDB.FriendList.COLUMN_NAME_NAME, name);
        values.put(FriendDB.FriendList.COLUMN_NAME_GENDER, gender);
        values.put(FriendDB.FriendList.COLUMN_NAME_PHONE, phone);
        values.put(FriendDB.FriendList.COLUMN_NAME_FAV, fav);

        // Which row to update, based on the ID
        String selection = FriendDB.FriendList.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(mId) };

        mDBRead.update(
                FriendDB.FriendList.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        mDBRead.close();

        goToFriends();
    }

    public void goToFriends(){
        Intent intent = new Intent(this, ShowFriends.class);
        startActivity(intent);
    }

    public void goToDelete(){
        mDBWrite = mDbHelper.getWritableDatabase();

        // Which row to update, based on the ID
        String selection = FriendDB.FriendList.COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(mId) };

        mDBWrite.delete(
                FriendDB.FriendList.TABLE_NAME,
                selection,
                selectionArgs);

        mDBWrite.close();
        goToFriends();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                return true;
            case R.id.action_delete:
                goToDelete();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
