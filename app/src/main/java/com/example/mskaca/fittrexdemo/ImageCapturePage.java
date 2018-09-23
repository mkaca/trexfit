package com.example.mskaca.fittrexdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

//import android

public class ImageCapturePage extends AppCompatActivity {

    private String photoFilePath = "";
    private int REQUEST_PERMISSIONS = 1;
    private int REQUEST_TAKE_PICTURE = 2;

    ImageView imageView;
    Button btnCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture_page);
        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        checkPermissions();

        btnCamera = (Button)findViewById(R.id.button1);
        imageView = (ImageView)findViewById(R.id.imageView1);

        // goes to menu testing page here
        Button menuBtn = findViewById(R.id.buttonToMenuEditor);
        menuBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(ImageCapturePage.this, MenuTestingActivity.class));
               }
           });

        // goes to menu testing page here
        Button camAPIBtn = findViewById(R.id.camAPIBtn);
        camAPIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageCapturePage.this, CameraPreviewTest.class));
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    // When startActivityForResult() is called, activity is launched. Once that activity finishes,
    // the calling activity is presented with a result within its onActivityResult() handler
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        btnCamera.setTextColor(Color.parseColor("#FF0F0F")); //custom color
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(90);
    }

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //takePhoto();    does nothing
        }
        else{
            String[] ary = Manifest.permission.WRITE_EXTERNAL_STORAGE.split("");
            ActivityCompat.requestPermissions(this, ary,REQUEST_PERMISSIONS);
        }
    }

}

