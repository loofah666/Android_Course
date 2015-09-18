package com.asus.peggy_lin.intentactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int CAMERA_CODE = 360;
    public ImageView cameraImage, profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraImage = (ImageView) findViewById(R.id.main_camera_view);
        profileImage = (ImageView) findViewById(R.id.main_profile_view);

    }

    public void goToCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooser = Intent.createChooser(intent, "Choose Camera");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(chooser, CAMERA_CODE);
    }
    public void goToCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooser = Intent.createChooser(intent, "Choose Camera");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(chooser, CAMERA_CODE);
    }

    public void goToEmail(View view){

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_CODE && (resultCode == RESULT_OK)){
                Bundle bundle = data.getExtras();
                Bitmap bmp = (Bitmap) bundle.get("data");
                if(bmp!=null) {
                    cameraImage.setImageBitmap(bmp);
                    profileImage.setImageBitmap(bmp);
                }
        }
    }

    //toggle ActionBar by clicking on Layout
    public void toggleActionBar(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getActionBar().isShowing())
                getActionBar().hide();
            else
                getActionBar().show();
        }
    }

    //avatar click enables photo switch
    public void switchProfilePhoto(View view) {
        final String[] arr = getResources().getStringArray(R.array.profile_array);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle(R.string.profile_action)
                .setItems(arr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        if(which==0)
                            goToCamera();
                        else
                            Toast.makeText(getApplicationContext(), arr[which], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
        //return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
