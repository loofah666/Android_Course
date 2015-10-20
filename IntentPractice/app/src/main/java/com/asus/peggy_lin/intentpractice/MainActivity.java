package com.asus.peggy_lin.intentpractice;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INTENT = "INTENT_PRAC";
    public static final int PICK_CONTACT_REQUEST = 1;  // The request code
    private Toolbar toolbar, toolbar_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(getString(R.string.group_title));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEmail();
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        toolbar_bottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        if(toolbar_bottom != null){
            setSupportActionBar(toolbar_bottom);
            toolbar_bottom.inflateMenu(R.menu.menu_bottom);
        }
    }

    public void goToNewPost(){

    }

    public void goToEmail(){
        String[] mail = {getString(R.string.email_address)};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));
        //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));

        String title = getResources().getString(R.string.chooser_email);
        Intent chooser = Intent.createChooser(emailIntent, title);

        startIntent(chooser);
    }

    public void goToCalendar(View view){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, 11, 31, 8, 30);  //month is 0~11, 11=December
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, 11, 31, 18, 30);

        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Adoption Center Year Annual Event");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Adoption Center");

        startIntent(calendarIntent);
    }

    public void goToContacts(){
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    public void goToTextMessaging(String number){
        Intent textIntent = new Intent(Intent.ACTION_SENDTO);
        textIntent.setType("text/plain");
        textIntent.setData(Uri.parse("sms:" + number));
        textIntent.putExtra("sms_body", getString(R.string.text_text));
//        textIntent.putExtra(Intent.EXTRA_STREAM, attachment);

        startIntent(textIntent);
    }

    public void startIntent(Intent intent){
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                Log.d(TAG_INTENT, "data.getData: " + contactUri);
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                Log.d(TAG_INTENT, "column int: " + Integer.toString(column));
                String number = cursor.getString(column);

                Log.d(TAG_INTENT, "number: " + number);
                goToTextMessaging(number);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.action_edit:
                goToNewPost();
                return true;
            case R.id.action_send:
                goToContacts();
                return true;
        }

        toolbar_bottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit_bottom:
                        return true;
                    case R.id.action_camera_bottom:
                        return true;
                }
                return true;
            }
        });

        return super.onOptionsItemSelected(item);
    }
}
