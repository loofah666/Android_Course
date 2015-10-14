package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class FriendAdd extends Activity {

    private FriendDBHelper mDbHelper;
    private SQLiteDatabase mDBWrite;
    EditText ed_name, ed_phone, ed_fav;
    RadioButton rb_boy, rb_girl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);

        mDbHelper = new FriendDBHelper(this);

        ed_name = (EditText) findViewById(R.id.edit_et_name);
        ed_phone = (EditText) findViewById(R.id.edit_et_phone);
        ed_fav = (EditText) findViewById(R.id.edit_et_fav);

        rb_boy = (RadioButton) findViewById(R.id.edit_rb_boy);
        rb_girl = (RadioButton) findViewById(R.id.edit_rb_girl);

    }

    public void saveNewFriend(View view){
        String name = ed_name.getText().toString();
        String phone = ed_phone.getText().toString();
        String fav = ed_fav.getText().toString();
        Integer gender = 0;
        if(rb_boy.isChecked())
            gender = 0;
        else if(rb_girl.isChecked())
            gender = 1;

        writeNewFriend(name, gender, phone, fav);
        goToFriends();

    }

    public void writeNewFriend(String name, Integer gender, String phone, String fav){
        mDBWrite = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FriendDB.FriendList.COLUMN_NAME_NAME, name);
        values.put(FriendDB.FriendList.COLUMN_NAME_GENDER, gender);
        values.put(FriendDB.FriendList.COLUMN_NAME_PHONE, phone);
        values.put(FriendDB.FriendList.COLUMN_NAME_FAV, fav);

        mDBWrite.insert(
                FriendDB.FriendList.TABLE_NAME,
                null,
                values);

        mDBWrite.close();
    }

    public void goToFriends(){
        Intent intent = new Intent(this, ShowFriends.class);
        startActivity(intent);
    }
}
