package com.asus.peggy_lin.intentactivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final int CAMERA_CODE = 360;
    public ImageView cameraImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraImage = (ImageView) findViewById(R.id.main_image_view);

    }

    public void goToCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooser = Intent.createChooser(intent, "Choose Camera");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(chooser, CAMERA_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_CODE && (resultCode == RESULT_OK)){
                Bundle bundle = data.getExtras();
                Bitmap bmp = (Bitmap) bundle.get("data");
                if(bmp!=null) {
                    cameraImage.setImageBitmap(bmp);
                }
        }
    }

    //toggle ActionBar by clicking on Layout
    public void toggleActionBar(View view){
        if(getActionBar().isShowing())
            getActionBar().hide();
        else
            getActionBar().show();
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
